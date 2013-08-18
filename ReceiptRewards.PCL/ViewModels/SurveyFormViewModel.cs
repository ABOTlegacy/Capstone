using ReceiptRewards.Core.ServiceAccessLayer.Database.Implementations;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations;
using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace ReceiptRewards.PCL.ViewModels {
    public class SurveyFormViewModel {

        // Instantiate Variables
        private int _companyId = 0;
        private int _surveyId = 0;
		private Survey _survey = new Survey();
        private Revision _revision = new Revision();
        private Submission _submission = new Submission();
        private Question _currentQuestion;
        private Action<string> _actionError = null;
        private Action<string> _actionSubmit = null;
        private Action _actionSaveQuestion = null;
        private Action _actionRefreshUI = null;
        private readonly SimpleRestService _simpleRestService = new SimpleRestService();


        
        // Getter / Setter of Survey Id
        public int surveyId {
            get { return this._surveyId; }
            set { this._surveyId = value; }
        }



        // Getter / Setter of Company Id
        public int companyId {
            get { return this._companyId; }
            set { this._companyId = value; }
        }



        // Getter / Setter of Survey
        public Survey survey {
            get { return this._survey; }
            set { this._survey = value; }
		}



        // Getter / Setter of Revision
        public Revision revision {
            get { return this._revision; }
            set {
                this._revision = value;
                this._submission.revisionId = this._revision.revisionId;
            }
        }



        // Getter / Setter of Submission
        public Submission submission {
            get { return this._submission; }
        }



        // Getter / Setter of Current Question
        public Question currentQuestion {
            get { return this._currentQuestion; }
            set { this._currentQuestion = value; }
        }



        // Getter / Setter of Error Action
        public Action<string> actionError {
            get { return this._actionError; }
            set { this._actionError = value; }
        }



        // Getter / Setter of Submit Action
        public Action<string> actionSubmit {
            get { return this._actionSubmit; }
            set { this._actionSubmit = value; }
        }



        // Getter / Setter of Save Question Action
        public Action actionSaveQuestion {
            get { return this._actionSaveQuestion; }
            set { this._actionSaveQuestion = value; }
        }



        // Getter / Setter of Refresh UI Action
        public Action actionRefreshUI {
            get { return this._actionRefreshUI; }
            set { this._actionRefreshUI = value; }
        }



        // Call The Async Action
        public void submit() {
            this._submission.dateCompleted = DateTime.Now.ToUniversalTime();
            this.sendFormSubmission();
        }



        // Call The Async Action
        public void refresh() {
            this.getSurvey();
            this._submission.dateStarted = DateTime.Now.ToUniversalTime();
        }



        // Call To Get The Survey
        private void getSurvey() {
            _simpleRestService.MakeRequest<Survey>(
                string.Format(SurveyServiceImpl.GET_BY_SURVEY_ID + this.surveyId),
                "GET",
                result => {
                    this.survey = result;
                    this.getRevision();
                },
                error => {
                    this._actionError("SURVEY: 01 = " + error.Message);
                    /* @TODO: CAll Error Method (this._actionError) */
                }
            );
        }



        // Call To Get The Revision
        private void getRevision() {
            _simpleRestService.MakeRequest<List<Revision>>(
                string.Format(ServiceAccessLayer.Database.Implementations.RevisionServiceImpl.GET_BY_ACTIVE_SURVEY_ID + this.survey.surveyId + "/" + 1),
                "GET",
                result => {
                    this.revision = result[0];
                    this.getQuestions();
                },
                error => {
                    this._actionError("REVISION: 01 = " + error.Message);
                    /* @TODO: CAll Error Method (this._actionError) */
                }
            );
        }


        // Call To Get The Questions
        private void getQuestions() {
            _simpleRestService.MakeRequest<List<Question>>(
                string.Format(ServiceAccessLayer.Database.Implementations.QuestionServiceImpl.GET_BY_REVISION_ID + this.revision.revisionId + "/" + 3),
                "GET",
                result => {
                    this.getRevisionGruntWork(result);
                }, error => {
                    this._actionError("REVISION: 01 = " + error.Message);
                    /* @TODO: CAll Error Method (this._actionError) */
                }
            );
        }



        private void getRevisionGruntWork(List<Question> allQuestions) {
            // Iterate Through All The Questions Only Once
            for (int i = 0; i < allQuestions.Count; i++) {
                for (int j = 0; j < allQuestions[i].form.formFlows.Count; j++) {
                    // Iterate through all the Questions
                    for (int h = 0; h < allQuestions.Count; h++) {
                        // If the targeted question id in the flow equals the question id then set that question
                        if (allQuestions[i].form.formFlows[j].question.questionId == allQuestions[h].questionId) {
                            allQuestions[i].form.formFlows[j].question = allQuestions[h];
                        }
                    }
                }

                // If Question Id equals the revision question id set the revision to that question
                if (revision.question.questionId == allQuestions[i].questionId) {
                    this.revision.question = allQuestions[i];
                    this.actionRefreshUI();
                }
            }
            this.currentQuestion = this.revision.question;
        }



        // Call To Get The List Of Questions
        public void actionNextQuestion() {
            // Instantiate Variables
            bool changed = false;
            Question defaultQuestion = null;

            // Cycle through formFlow
            foreach(FormFlow formFlow in this.currentQuestion.form.formFlows) {

                // Find the Default Question
                if(formFlow.value == "") {
                    defaultQuestion = formFlow.question;
                }

                // Compare the FormFlows with the SubmissionFlows
                foreach(SubmissionAnswer submissionAnswer in this.submission.submissionAnswers) {
// @TODO: Had to just use the currentquestionid for this. would have liked to use the formflow formid or quesion id, but this will be fine for now.
                    if (! changed && (this.currentQuestion.questionId == submissionAnswer.questionId) && (formFlow.value == submissionAnswer.value)) {
                        changed = true;
                        this.currentQuestion = formFlow.question;
                    }
                }
            }

            // Else Set Default Next
            if(! changed) {
                this.currentQuestion = defaultQuestion;
            }

            // Refresh UI
            this.actionRefreshUI();
        }












        // Call The To Save Submission
        private void sendFormSubmission() {
            _simpleRestService.MakeRequestObj<int>(
                 string.Format(SubmissionServiceImpl.CREATE),
                 "POST",
                 this._submission,
                 result => {
                     this._submission.submissionId = result;
                     if(this._submission.submissionId == null || this._submission.submissionId <= 0) {
                         this._actionError("FORMSUBMISSION: SubmissionID was Not Created = ");
                     }
                     this._sendSubmissionAutomation();
                 },
                 error => {
                     this._actionError("FORMSUBMISSION: 01 = " + error.Message);
                     this._submission.submissionAnswers.Clear();
                     /* @TODO: CAll Error Method (this._actionError) */
                 }
             );
        }

        // Call The To Start Automation
        private void _sendSubmissionAutomation() {
            _simpleRestService.MakeRequest<Submission>(
                 string.Format(SubmissionServiceImpl.ACTION_BY_SUBBMISSION + this._submission.submissionId),
                 "GET",
                 result => {
                     this._actionSubmit("FFF");
                 },
                 error => {
                     this._actionError("FORMAUTOMATION: 01 = " + error.Message);
                     this._submission.submissionAnswers.Clear();
                     /* @TODO: CAll Error Method (this._actionError) */
                 }
             );
        }
    }
}
