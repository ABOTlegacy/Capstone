using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using Phone.Controls;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media.Imaging;

namespace ReceiptRewards.Phone.PanoramaModels {

    public class SurveyFormPanoramaModel {

        // Instantiate Global Variables
        private static SurveyFormViewModel _viewModel = new SurveyFormViewModel();
        private static PhoneApplicationPage _page = null;
        private static List<UIElement> _formElementCollection = new List<UIElement>();



        /**
         * Browse All Surveys Panorama Item
         */
        public static PivotItem generate(PhoneApplicationPage page) {
            // Initialize Variables
            String title = "Survey";
            PivotItem pi = new PivotItem();
            SurveyFormPanoramaModel._page = page;

            // Set State Values
            SurveyFormPanoramaModel._viewModel.surveyId = (int) PhoneApplicationService.Current.State["surveyId"];
            SurveyFormPanoramaModel._viewModel.companyId = (int) PhoneApplicationService.Current.State["companyId"];

            // Create Panorama Item
            pi.Header = title;
            pi.Loaded += SurveyFormPanoramaModel.loadForm;
            pi.Width = 460;

            // Add Stack Panel
            StackPanel sp = new StackPanel();
            sp.Name = "spFormContainer";
            
            // Add Error Message Block
            TextBlock txtError = new TextBlock();
            txtError.Name = "txtError";
            txtError.Text = "Error";
            txtError.TextWrapping = TextWrapping.Wrap;
            txtError.Visibility = Visibility.Collapsed;
            sp.Children.Add(txtError);

            // Add Progress Bar
            ProgressBar pbLoading = new ProgressBar();
            pbLoading.Name = "pbLoading";
            pbLoading.Visibility = Visibility.Visible;
            pbLoading.IsIndeterminate = true;
            sp.Children.Add(pbLoading);

            // Add Question Stack Panel
            StackPanel spQuestion = new StackPanel();
            spQuestion.Name = "spQuestionContainer";
            sp.Children.Add(spQuestion);

            // ** Add Submit Survey Panel ** //
            StackPanel spSubmitSurveyContainer = new StackPanel();
            spSubmitSurveyContainer.Name = "spSubmitSurveyContainer";
            spSubmitSurveyContainer.Visibility = Visibility.Collapsed;
            sp.Children.Add(spSubmitSurveyContainer);

            // Logo
            Image image = new Image();
            BitmapImage bitmapImage = new BitmapImage(new Uri("/Resources/Images/Logo/ReceiptRewards_Logo_Color.png", UriKind.Relative));
            image.Source = bitmapImage;
            image.Height = double.NaN;
            image.Width = double.NaN;
            image.Name = "imgBanner";
            spSubmitSurveyContainer.Children.Add(image);

            // Text Block
            TextBlock txtFinishSurvey = new TextBlock();
            txtFinishSurvey.Text = "You've Completed all the Questions!\n\n\nAll that is left to do is SUBMIT the survey and claim your REWARDS!";
            spSubmitSurveyContainer.Children.Add(txtFinishSurvey);

            // Submit Survey Button
            Button btnSubmit = new Button();
            btnSubmit.Click += (o, k) => {
                SurveyFormPanoramaModel._viewModel.submit();
            };
            TextBlock lblSubmit = new TextBlock();
            lblSubmit.Text = "Submit Survey";
            btnSubmit.Content = lblSubmit;
            btnSubmit.Name = "btnSubmit";
            spSubmitSurveyContainer.Children.Add(btnSubmit);

            // Next Question Button
            Button btnNextQuestion = new Button();
            btnNextQuestion.Click += (o, k) => {
                SurveyFormPanoramaModel._viewModel.actionSaveQuestion();
            };
            TextBlock lblNextQuestion = new TextBlock();
            lblNextQuestion.Text = "Next Question";
            btnNextQuestion.Content = lblNextQuestion;
            btnNextQuestion.Name = "btnNextQuestion";
            btnNextQuestion.Visibility = Visibility.Collapsed;
            sp.Children.Add(btnNextQuestion);

            // Add Scroll View
            ScrollViewer scrollView = new ScrollViewer();
            scrollView.Content = sp;

            // Add Content
            pi.Content = scrollView;
            return pi;
        }



        /********************** Event Handlers *************************/
        private static void loadForm(Object sender, RoutedEventArgs e) {
            // Get The Panorama Item for Global View and Manipulation Control
            PivotItem pi = ((PivotItem) sender);

            // Start the Progress Bar
            Deployment.Current.Dispatcher.BeginInvoke(() => {
                ProgressBar pbLoading = (ProgressBar) pi.FindName("pbLoading");
                pbLoading.Visibility = Visibility.Visible;
            });

            // Call Thread
            ThreadPool.QueueUserWorkItem(o => SurveyFormPanoramaModel.slowMethod(pi));
        }



        private static void slowMethod(PivotItem pi) {
            
            // Set The Submit Action
            SurveyFormPanoramaModel._viewModel.actionSubmit = (value => {
                // Sends Request to the UI Thread To Update
                Deployment.Current.Dispatcher.BeginInvoke(() => {
                    SurveyFormPanoramaModel._page.NavigationService.Navigate(new Uri("/SurveySuccessPage.xaml", UriKind.Relative)); 
                });
            });



            // Set The Error Action
            SurveyFormPanoramaModel._viewModel.actionError = (value => {
                Deployment.Current.Dispatcher.BeginInvoke(() => {
                    TextBlock txtTest = (TextBlock) pi.FindName("txtError");
                    txtTest.Text = "ERROR: " + value;
                    txtTest.Visibility = Visibility.Visible;
                });
            });



            // Set The Save Question Action
            SurveyFormPanoramaModel._viewModel.actionSaveQuestion = () => {

                // Save all the answers upon submit
                foreach (UIElement element in SurveyFormPanoramaModel._formElementCollection) {

                    // Submission Answer
                    SubmissionAnswer formAnswer = new SubmissionAnswer();
                    formAnswer.questionId = SurveyFormPanoramaModel._viewModel.currentQuestion.questionId;

                    // Textbox
                    if (element is TextBox) {
                        formAnswer.formElementId = int.Parse(((TextBox) element).Name);
                        formAnswer.value = ((TextBox) element).Text;

                    // Radio Button
                    } else if (element is TextBlock) {
                        if (((TextBlock) element).Visibility == Visibility.Collapsed) {
                            formAnswer.formElementId = int.Parse(((TextBlock) element).Name);
                            for (int i = 0; i < SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements.Count; i++) {
                                if (formAnswer.formElementId == SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements[i].formElementId) {
                                    for (int j = 0; j < SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements[i].formElementOptions.Count; j++) {
                                        if (((TextBlock)element).Text.Length > 0 && int.Parse(((TextBlock)element).Text) == SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements[i].formElementOptions[j].formElementOptionId) {
                                            formAnswer.value = SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements[i].formElementOptions[j].value;
                                        }
                                    }
                                }
                            }
                        }

                    // Slider Rating
                    } else if (element is Slider) {
                        formAnswer.formElementId = int.Parse(((Slider) element).Name);
                        for (int i = 0; i < SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements.Count; i++) {
                            if (formAnswer.formElementId == SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements[i].formElementId) {
                                formAnswer.value = SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements[i].formElementOptions[int.Parse(((Slider)element).Value + "")].value;
                            }
                        }

                    // PickerBox
                    } else if (element is PickerBoxDialog) {
                        formAnswer.formElementId = int.Parse(((PickerBoxDialog) element).Name);
                        formAnswer.value = (string) ((PickerBoxDialog) element).SelectedItem;
                    }

                    // Add Answer
                    if (formAnswer != null) {
                        SurveyFormPanoramaModel._viewModel.submission.submissionAnswers.Add(formAnswer);
                    }
                    /*SurveyFormPanoramaModel._viewModel.actionError("FormAnswer: \n" + 
                                                                    "QuestionID: " + formAnswer.questionId + "\n" +
                                                                    "Value: " + formAnswer.value +  "\n" +
                                                                    "UIElement Length: " + SurveyFormPanoramaModel._formElementCollection.Count + "\n" +
                                                                    "");*/
                }

                // Go To Next Question
                SurveyFormPanoramaModel._viewModel.actionNextQuestion();
            };



            // Set The Action
            SurveyFormPanoramaModel._viewModel.actionRefreshUI = () => {

                // Sends Request to the UI Thread To Update
                Deployment.Current.Dispatcher.BeginInvoke(() => {

                    // Clear the UI Element List
                    SurveyFormPanoramaModel._formElementCollection.Clear();

                    // Progress Bar
                    ProgressBar pbLoading = (ProgressBar) pi.FindName("pbLoading");
                    pbLoading.Visibility = Visibility.Collapsed;

                    // If CurrentQuestion is null, then last question so dispaly submit survey stackpanel
                    if(SurveyFormPanoramaModel._viewModel.currentQuestion == null) {
                        StackPanel spSubmitSurveyContainer = (StackPanel) pi.FindName("spSubmitSurveyContainer");
                        spSubmitSurveyContainer.Visibility = Visibility.Visible;
                        StackPanel spQuestionContainer = (StackPanel) pi.FindName("spQuestionContainer");
                        spQuestionContainer.Visibility = Visibility.Collapsed;
                        Button btnNextQuestion = (Button) pi.FindName("btnNextQuestion");
                        btnNextQuestion.Visibility = Visibility.Collapsed;

                    } else {
                        // If No FormElements in form, then add the questionid as an submissionanswer and move to next question
                        if(SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements.Count == 0) {
                            SubmissionAnswer submissionAnswer = new SubmissionAnswer();
                            submissionAnswer.questionId = SurveyFormPanoramaModel._viewModel.currentQuestion.questionId;
                            SurveyFormPanoramaModel._viewModel.submission.submissionAnswers.Add(submissionAnswer);
                            SurveyFormPanoramaModel._viewModel.actionNextQuestion();

                        // Display the Next Question
                        } else {

                            // Get the Stack Panel
                            StackPanel sp = (StackPanel) pi.FindName("spQuestionContainer");
                            sp.Children.Clear();

                            // Next Button
                            Button btnNextQuestion = (Button) pi.FindName("btnNextQuestion");
                            btnNextQuestion.Visibility = Visibility.Visible;

                            // Cycle through all the form elements of the form
                            foreach (FormElement formElement in SurveyFormPanoramaModel._viewModel.currentQuestion.form.formElements) {
                                // Variables
                                UIElement element;
                                bool neededOnSubmit = false; // @TODO: Temporarily Needed Variable, replace with a property of the formElement

                                // @TODO: Need to have it pass the name of the code instead of having to rely on the actual codeId
                                // Label
                                if(formElement.code.codeId == 20) {
                                    element = new TextBlock();
                                    ((TextBlock) element).Name = "Element_" + formElement.formElementId;
                                    ((TextBlock) element).Text = formElement.formElementOptions[0].displayText.displayTextTranslation;
                                    ((TextBlock) element).TextWrapping = TextWrapping.Wrap;

                                // TextBox
                                } else if(formElement.code.codeId == 2) {
                                    element = new TextBox();
                                    ((TextBox) element).Name = "" + formElement.formElementId;
                                    ((TextBox) element).TextWrapping = TextWrapping.Wrap;
                                    neededOnSubmit = true;

                                // Divider
                                } else if (formElement.code.codeId == 22) {
                                    element = new TextBlock();
                                    ((TextBlock) element).Name = "Element_" + formElement.formElementId;
                                    ((TextBlock) element).Text = "__________________"; // @TODO: only a temporary solution for divider

                                // Radio Group
                                } else if (formElement.code.codeId == 21) {
                                    element = new StackPanel();
                                    ((StackPanel) element).Orientation = Orientation.Vertical;
                                    TextBlock hdnRadio = new TextBlock();
                                    hdnRadio.Visibility = Visibility.Collapsed;
                                    hdnRadio.Name = formElement.formElementId + "";
                                    SurveyFormPanoramaModel._formElementCollection.Add(hdnRadio);
                                    foreach(FormElementOption option in formElement.formElementOptions) { 
                                        StackPanel panel = new StackPanel();
                                        panel.Orientation = Orientation.Horizontal;
                                        RadioButton rb = new RadioButton();
                                        rb.GroupName = formElement.formElementId + "";
                                        rb.Checked += (o, e) => {
                                            hdnRadio.Text = option.formElementOptionId + "";
                                        };
                                        TextBlock label = new TextBlock();
                                        label.Text = option.displayText.displayTextTranslation;
                                        panel.Children.Add(rb);
                                        panel.Children.Add(label);
                                        ((StackPanel) element).Children.Add(panel);
                                    }

                                // Checkbox Group
                                } else if (formElement.code.codeId == 44) {
                                    element = new StackPanel();
                                    ((StackPanel)element).Orientation = Orientation.Vertical;
                                    TextBlock hdnRadio = new TextBlock();
                                    hdnRadio.Visibility = Visibility.Collapsed;
                                    hdnRadio.Name = formElement.formElementId + "";
                                    SurveyFormPanoramaModel._formElementCollection.Add(hdnRadio);
                                    foreach (FormElementOption option in formElement.formElementOptions) {
                                        StackPanel panel = new StackPanel();
                                        panel.Orientation = Orientation.Horizontal;
                                        CheckBox rb = new CheckBox();
                                        rb.Name = formElement.formElementId + "";
                                        rb.Checked += (o, e) => {
                                            hdnRadio.Text = option.formElementOptionId + "";
                                        };
                                        TextBlock label = new TextBlock();
                                        label.Text = option.displayText.displayTextTranslation;
                                        panel.Children.Add(rb);
                                        panel.Children.Add(label);
                                        ((StackPanel)element).Children.Add(panel);
                                    }

                                // Slider
                                } else if (formElement.code.codeId == 23) {
                                    element = new Slider();
                                    ((Slider) element).Name = "" + formElement.formElementId;
                                    ((Slider) element).Minimum = 0;
                                    ((Slider) element).Maximum = formElement.formElementOptions.Count - 1; 
                                    ((Slider) element).ValueChanged += (o, e) => {
                                        Slider slider = (Slider) o;
                                        for(double i = slider.Minimum; i <= slider.Maximum; i++) {
                                            if((i - .5) <= slider.Value && slider.Value <= (i + .5)) {
                                                slider.Value = i;
                                            }
                                        }
                                    };
                                    neededOnSubmit = true;

                                // Textarea
                                } else if (formElement.code.codeId == 24) {
                                    element = new TextBox();
                                    ((TextBox) element).Name = "" + formElement.formElementId;
                                    ((TextBox) element).TextWrapping = TextWrapping.Wrap;
                                    ((TextBox) element).MinHeight = 100;
                                    neededOnSubmit = true;

                                // List Picker
                                } else if (formElement.code.codeId == 28) {
                                    element = new PickerBoxDialog();
                                    ((PickerBoxDialog) element).Name = "" + formElement.formElementId;
                                    List<string> lpItems = new List<string>();
                                    ((PickerBoxDialog) element).Title = "Label";
                                    foreach (FormElementOption option in formElement.formElementOptions) {
                                        lpItems.Add(option.displayText.displayTextTranslation);
                                    }
                                    ((PickerBoxDialog) element).ItemSource = lpItems;
                                    Button btn = new Button();
                                    btn.Width = 438;
                                    btn.Click += (o, e) => {
                                        ((PickerBoxDialog) element).Show();
                                    };
                                    ((PickerBoxDialog) element).SelectedIndex = 0;
                                    TextBlock tltlt = new TextBlock();
                                    tltlt.Text = lpItems[0];
                                    btn.Content = tltlt;
                                    sp.Children.Add(btn);
                                    ((PickerBoxDialog) element).SelectedIndex = 0;
                                    ((PickerBoxDialog) element).Closed += (o, e) => {
                                        TextBlock tb = new TextBlock();
                                        tb.Text = ((string) ((PickerBoxDialog) o).SelectedItem);
                                        btn.Content = tb;
                                    };
                                    neededOnSubmit = true;

                                // Not Found
                                } else {
                                    element = new TextBlock();
                                    ((TextBlock) element).Text = "FAILED - " + formElement.code.codeId;
                                }

                                // Add To StackPanel
                                if (formElement.code.codeId != 28) {
                                sp.Children.Add(element);
                                }
                                // Needed to Be Added on Submit
                                if(neededOnSubmit) {
                                    SurveyFormPanoramaModel._formElementCollection.Add(element);
                                }
                            }
                        }
                    }
                });
            };

            // Start the Loading of Elements
            SurveyFormPanoramaModel._viewModel.refresh();

            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);
        }
    }
}
