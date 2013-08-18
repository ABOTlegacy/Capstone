using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.Plugins.Json;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.BusinessLayer {

    public class AliasHelper {



        public static Automation getAutomation(A a) {
            if (a == null) { return null; }
            Automation automation = new Automation();
            automation.automationId = a.a1;
            automation.commandElements = AliasHelper.getCommandElementArray(a.a2);
            automation.automationFlows = AliasHelper.getAutomationFlowArray(a.a3);
            return automation;
        }
        public static A getAutomationAlias(Automation automation) {
            if (automation == null) { return null; }
            A a = new A();
            a.a1 = automation.automationId;
            a.a2 = AliasHelper.getCommandElementAliasArray(automation.commandElements);
            a.a3 = AliasHelper.getAutomationFlowAliasArray(automation.automationFlows);
            return a;
        }
        public static List<Automation> getAutomationArray(List<A> aArray) {
            List<Automation> automationArray = new List<Automation>();
            foreach(A a in aArray) {
                automationArray.Add(AliasHelper.getAutomation(a));
            }
            return automationArray;
        }
        public static List<A> getAutomationAliasArray(List<Automation> automationArray) {
            List<A> aArray = new List<A>();
            foreach(Automation automation in automationArray) {
                aArray.Add(AliasHelper.getAutomationAlias(automation));
            }
            return aArray;
        }



        public static AutomationFlow getAutomationFlow(B b) {
            if (b == null) { return null; }
            AutomationFlow automationFlow = new AutomationFlow();
            automationFlow.automationFlowId = b.b1;
            automationFlow.automationId = b.b2;
            automationFlow.question = AliasHelper.getQuestion(b.b3);
            automationFlow.weight = b.b4;
            return automationFlow;
        }
        public static B getAutomationFlowAlias(AutomationFlow automationFlow) {
            if (automationFlow == null) { return null; }
            B b = new B();
            b.b1 = automationFlow.automationFlowId;
            b.b2 = automationFlow.automationId;
            b.b3 = AliasHelper.getQuestionAlias(automationFlow.question);
            b.b4 = automationFlow.weight;
            return b;
        }
        public static List<AutomationFlow> getAutomationFlowArray(List<B> bArray) {
            List<AutomationFlow> automationFlowArray = new List<AutomationFlow>();
            foreach(B b in bArray) {
                automationFlowArray.Add(AliasHelper.getAutomationFlow(b));
            }
            return automationFlowArray;
        }
        public static List<B> getAutomationFlowAliasArray(List<AutomationFlow> automationFlowArray) {
            List<B> bArray = new List<B>();
            foreach(AutomationFlow automationFlow in automationFlowArray) {
                bArray.Add(AliasHelper.getAutomationFlowAlias(automationFlow));
            }
            return bArray;
        }



        public static Company getCompany(H h) {
            if (h == null) { return null; }
            Company company = new Company();
            company.companyId = h.h1;
            company.name = h.h2;
            company.surveys = AliasHelper.getSurveyArray(h.h3);
            return company;
        }
        public static H getCompanyAlias(Company company) {
            if (company == null) { return null; }
            H h = new H();
            h.h1 = company.companyId;
            h.h2 = company.name;
            h.h3 = AliasHelper.getSurveyAliasArray(company.surveys);
            return h;
        }
        public static List<Company> getCompanyArray(List<H> hArray) {
            List<Company> companyArray = new List<Company>();
            foreach(H h in hArray) {
                companyArray.Add(AliasHelper.getCompany(h));
            }
            return companyArray;
        }
        public static List<H> getCompanyAliasArray(List<Company> companyArray) {
            List<H> hArray = new List<H>();
            foreach(Company company in companyArray) {
                hArray.Add(AliasHelper.getCompanyAlias(company));
            }
            return hArray;
        }



        public static Survey getSurvey(U u) {
            if (u == null) { return null; }
            Survey survey = new Survey();
            survey.surveyId = u.u1;
            survey.companyId = u.u2;
            survey.name = u.u3;
            survey.revisions = AliasHelper.getRevisionArray(u.u4);
            return survey;
        }
        public static U getSurveyAlias(Survey survey) {
            if (survey == null) { return null; }
            U u = new U();
            u.u1 = survey.surveyId;
            u.u2 = survey.companyId;
            u.u3 = survey.name;
            u.u4 = AliasHelper.getRevisionAliasArray(survey.revisions);
            return u;
        }
        public static List<Survey> getSurveyArray(List<U> uArray) {
            List<Survey> surveyArray = new List<Survey>();
            foreach(U u in uArray) {
                surveyArray.Add(AliasHelper.getSurvey(u));
            }
            return surveyArray;
        }
        public static List<U> getSurveyAliasArray(List<Survey> surveyArray) {
            List<U> uArray = new List<U>();
            foreach(Survey survey in surveyArray) {
                uArray.Add(AliasHelper.getSurveyAlias(survey));
            }
            return uArray;
        }



        public static Code getCode(C c) {
            if (c == null) { return null; }
            Code code = new Code();
            code.codeId = c.c1;
            code.code = c.c2;
            code.name = c.c3;
            code.description = c.c4;
            code.codeType = AliasHelper.getCodeType(c.c5);
            return code;
        }
        public static C getCodeAlias(Code code) {
            if (code == null) { return null; }
            C c = new C();
            c.c1 = code.codeId;
            c.c2 = code.code;
            c.c3 = code.name;
            c.c4 = code.description;
            c.c5 = AliasHelper.getCodeTypeAlias(code.codeType);
            return c;
        }
        public static List<Code> getCodeArray(List<C> cArray) {
            List<Code> codeArray = new List<Code>();
            foreach(C c in cArray) {
                codeArray.Add(AliasHelper.getCode(c));
            }
            return codeArray;
        }
        public static List<C> getCodeAliasArray(List<Code> codeArray) {
            List<C> cArray = new List<C>();
            foreach(Code code in codeArray) {
                cArray.Add(AliasHelper.getCodeAlias(code));
            }
            return cArray;
        }



        public static CodeType getCodeType(D d) {
            if (d == null) { return null; }
            CodeType codeType = new CodeType();
            codeType.codeTypeId = d.d1;
            codeType.type = d.d2;
            codeType.description = d.d3;
            return codeType;
        }
        public static D getCodeTypeAlias(CodeType codeType) {
            if (codeType == null) { return null; }
            D d = new D();
            d.d1 = codeType.codeTypeId;
            d.d2 = codeType.type;
            d.d3 = codeType.description;
            return d;
        }
        public static List<CodeType> getCodeTypeArray(List<D> dArray) {
            List<CodeType> codeTypeArray = new List<CodeType>();
            foreach(D d in dArray) {
                codeTypeArray.Add(AliasHelper.getCodeType(d));
            }
            return codeTypeArray;
        }
        public static List<D> getCodeTypeAliasArray(List<CodeType> codeTypeArray) {
            List<D> dArray = new List<D>();
            foreach(CodeType codeType in codeTypeArray) {
                dArray.Add(AliasHelper.getCodeTypeAlias(codeType));
            }
            return dArray;
        }



        public static CommandElement getCommandElement(E e) {
            if (e == null) { return null; }
            CommandElement commandElement = new CommandElement();
            commandElement.commandElementId = e.e1;
            commandElement.code = AliasHelper.getCode(e.e2);
            commandElement.commandIdentifiers = AliasHelper.getCommandIdentifierArray(e.e3);
            commandElement.formElements = AliasHelper.getCommandFormElementRelationArray(e.e4);
            commandElement.testData = e.e5;
            return commandElement;
        }
        public static E getCommandElementAlias(CommandElement commandElement) {
            if (commandElement == null) { return null; }
            E e = new E();
            e.e1 = commandElement.commandElementId;
            e.e2 = AliasHelper.getCodeAlias(commandElement.code);
            e.e3 = AliasHelper.getCommandIdentifierAliasArray(commandElement.commandIdentifiers);
            e.e4 = AliasHelper.getCommandFormElementRelationAliasArray(commandElement.formElements);
            e.e5 = commandElement.testData;
            return e;
        }
        public static List<CommandElement> getCommandElementArray(List<E> eArray) {
            List<CommandElement> commandElementArray = new List<CommandElement>();
            foreach(E e in eArray) {
                commandElementArray.Add(AliasHelper.getCommandElement(e));
            }
            return commandElementArray;
        }
        public static List<E> getCommandElementAliasArray(List<CommandElement> commandElementArray) {
            List<E> eArray = new List<E>();
            foreach(CommandElement commandElement in commandElementArray) {
                eArray.Add(AliasHelper.getCommandElementAlias(commandElement));
            }
            return eArray;
        }



        public static CommandFormElementRelation getCommandFormElementRelation(F f) {
            if (f == null) { return null; }
            CommandFormElementRelation commandFormElementRelation = new CommandFormElementRelation();
            commandFormElementRelation.commandFormElementRelationId = f.f1;
            commandFormElementRelation.commandElementId = f.f2;
            commandFormElementRelation.formElementId = f.f3;
            return commandFormElementRelation;
        }
        public static F getCommandFormElementRelationAlias(CommandFormElementRelation commandFormElementRelation) {
            if (commandFormElementRelation == null) { return null; }
            F f = new F();
            f.f1 = commandFormElementRelation.commandFormElementRelationId;
            f.f2 = commandFormElementRelation.commandElementId;
            f.f3 = commandFormElementRelation.formElementId;
            return f;
        }
        public static List<CommandFormElementRelation> getCommandFormElementRelationArray(List<F> fArray) {
            List<CommandFormElementRelation> commandFormElementRelationArray = new List<CommandFormElementRelation>();
            foreach(F f in fArray) {
                commandFormElementRelationArray.Add(AliasHelper.getCommandFormElementRelation(f));
            }
            return commandFormElementRelationArray;
        }
        public static List<F> getCommandFormElementRelationAliasArray(List<CommandFormElementRelation> commandFormElementRelationArray) {
            List<F> fArray = new List<F>();
            foreach(CommandFormElementRelation commandFormElementRelation in commandFormElementRelationArray) {
                fArray.Add(AliasHelper.getCommandFormElementRelationAlias(commandFormElementRelation));
            }
            return fArray;
        }



        public static CommandIdentifier getCommandIdentifier(G g) {
            if (g == null) { return null; }
            CommandIdentifier commandIdentifier = new CommandIdentifier();
            commandIdentifier.commandIdentifierId = g.g1;
            commandIdentifier.commandElementId = g.g2;
            commandIdentifier.code = AliasHelper.getCode(g.g3);
            commandIdentifier.value = g.g4;
            return commandIdentifier;
        }
        public static G getCommandIdentifierAlias(CommandIdentifier commandIdentifier) {
            if (commandIdentifier == null) { return null; }
            G g = new G();
            g.g1 = commandIdentifier.commandIdentifierId;
            g.g2 = commandIdentifier.commandElementId;
            g.g3 = AliasHelper.getCodeAlias(commandIdentifier.code);
            g.g4 = commandIdentifier.value;
            return g;
        }
        public static List<CommandIdentifier> getCommandIdentifierArray(List<G> gArray) {
            List<CommandIdentifier> commandIdentifierArray = new List<CommandIdentifier>();
            foreach(G g in gArray) {
                commandIdentifierArray.Add(AliasHelper.getCommandIdentifier(g));
            }
            return commandIdentifierArray;
        }
        public static List<G> getCommandIdentifierAliasArray(List<CommandIdentifier> commandIdentifierArray) {
            List<G> gArray = new List<G>();
            foreach(CommandIdentifier commandIdentifier in commandIdentifierArray) {
                gArray.Add(AliasHelper.getCommandIdentifierAlias(commandIdentifier));
            }
            return gArray;
        }



        public static DisplayText getDisplayText(I i) {
            if (i == null) { return null; }
            DisplayText displayText = new DisplayText();
            displayText.displayTextId = i.i1;
            displayText.displayTextTranslation = i.i2;
            displayText.displayTextTranslations = AliasHelper.getDisplayTextTranslationArray(i.i3);
            return displayText;
        }
        public static I getDisplayTextAlias(DisplayText displayText) {
            if (displayText == null) { return null; }
            I i = new I();
            i.i1 = displayText.displayTextId;
            i.i2 = displayText.displayTextTranslation;
            i.i3 = AliasHelper.getDisplayTextTranslationAliasArray(displayText.displayTextTranslations);
            return i;
        }
        public static List<DisplayText> getDisplayTextArray(List<I> iArray) {
            List<DisplayText> displayTextArray = new List<DisplayText>();
            foreach(I i in iArray) {
                displayTextArray.Add(AliasHelper.getDisplayText(i));
            }
            return displayTextArray;
        }
        public static List<I> getDisplayTextAliasArray(List<DisplayText> displayTextArray) {
            List<I> iArray = new List<I>();
            foreach(DisplayText displayText in displayTextArray) {
                iArray.Add(AliasHelper.getDisplayTextAlias(displayText));
            }
            return iArray;
        }



        public static DisplayTextTranslation getDisplayTextTranslation(J j) {
            if (j == null) { return null; }
            DisplayTextTranslation displayTextTranslation = new DisplayTextTranslation();
            displayTextTranslation.displayTextTranslationId = j.j1;
            displayTextTranslation.displayTextId = j.j2;
            displayTextTranslation.code = AliasHelper.getCode(j.j3);
            displayTextTranslation.value = j.j4;
            return displayTextTranslation;
        }
        public static J getDisplayTextTranslationAlias(DisplayTextTranslation displayTextTranslation) {
            if (displayTextTranslation == null) { return null; }
            J j = new J();
            j.j1 = displayTextTranslation.displayTextTranslationId;
            j.j2 = displayTextTranslation.displayTextId;
            j.j3 = AliasHelper.getCodeAlias(displayTextTranslation.code);
            j.j4 = displayTextTranslation.value;
            return j;
        }
        public static List<DisplayTextTranslation> getDisplayTextTranslationArray(List<J> jArray) {
            List<DisplayTextTranslation> displayTextTranslationArray = new List<DisplayTextTranslation>();
            foreach(J j in jArray) {
                displayTextTranslationArray.Add(AliasHelper.getDisplayTextTranslation(j));
            }
            return displayTextTranslationArray;
        }
        public static List<J> getDisplayTextTranslationAliasArray(List<DisplayTextTranslation> displayTextTranslationArray) {
            List<J> jArray = new List<J>();
            foreach(DisplayTextTranslation displayTextTranslation in displayTextTranslationArray) {
                jArray.Add(AliasHelper.getDisplayTextTranslationAlias(displayTextTranslation));
            }
            return jArray;
        }



        public static Form getForm(K k) {
            if (k == null) { return null; }
            Form form = new Form();
            form.formId = k.k1;
            form.formElements = AliasHelper.getFormElementArray(k.k2);
            form.formFlows = AliasHelper.getFormFlowArray(k.k3);
            return form;
        }
        public static K getFormAlias(Form form) {
            if (form == null) { return null; }
            K k = new K();
            k.k1 = form.formId;
            k.k2 = AliasHelper.getFormElementAliasArray(form.formElements);
            k.k3 = AliasHelper.getFormFlowAliasArray(form.formFlows);
            return k;
        }
        public static List<Form> getFormArray(List<K> kArray) {
            List<Form> formArray = new List<Form>();
            foreach(K k in kArray) {
                formArray.Add(AliasHelper.getForm(k));
            }
            return formArray;
        }
        public static List<K> getFormAliasArray(List<Form> formArray) {
            List<K> kArray = new List<K>();
            foreach(Form form in formArray) {
                kArray.Add(AliasHelper.getFormAlias(form));
            }
            return kArray;
        }



        public static FormElement getFormElement(L l) {
            if (l == null) { return null; }
            FormElement formElement = new FormElement();
            formElement.formElementId = l.l1;
            formElement.code = AliasHelper.getCode(l.l2);
            formElement.formElementOptions = AliasHelper.getFormElementOptionArray(l.l3);
            formElement.formElementAttributes = AliasHelper.getFormElementAttributeArray(l.l4);
            return formElement;
        }
        public static L getFormElementAlias(FormElement formElement) {
            if (formElement == null) { return null; }
            L l = new L();
            l.l1 = formElement.formElementId;
            l.l2 = AliasHelper.getCodeAlias(formElement.code);
            l.l3 = AliasHelper.getFormElementOptionAliasArray(formElement.formElementOptions);
            l.l4 = AliasHelper.getFormElementAttributeAliasArray(formElement.formElementAttributes);
            return l;
        }
        public static List<FormElement> getFormElementArray(List<L> lArray) {
            List<FormElement> formElementArray = new List<FormElement>();
            foreach(L l in lArray) {
                formElementArray.Add(AliasHelper.getFormElement(l));
            }
            return formElementArray;
        }
        public static List<L> getFormElementAliasArray(List<FormElement> formElementArray) {
            List<L> lArray = new List<L>();
            foreach(FormElement formElement in formElementArray) {
                lArray.Add(AliasHelper.getFormElementAlias(formElement));
            }
            return lArray;
        }



        public static FormElementAttribute getFormElementAttribute(M m) {
            if (m == null) { return null; }
            FormElementAttribute formElementAttribute = new FormElementAttribute();
            formElementAttribute.formElementAttributeId = m.m1;
            formElementAttribute.formElementId = m.m2;
            formElementAttribute.code = AliasHelper.getCode(m.m3);
            formElementAttribute.value = m.m4;
            return formElementAttribute;
        }
        public static M getFormElementAttributeAlias(FormElementAttribute formElementAttribute) {
            if (formElementAttribute == null) { return null; }
            M m = new M();
            m.m1 = formElementAttribute.formElementAttributeId;
            m.m2 = formElementAttribute.formElementId;
            m.m3 = AliasHelper.getCodeAlias(formElementAttribute.code);
            m.m4 = formElementAttribute.value;
            return m;
        }
        public static List<FormElementAttribute> getFormElementAttributeArray(List<M> mArray) {
            List<FormElementAttribute> formElementAttributeArray = new List<FormElementAttribute>();
            foreach(M m in mArray) {
                formElementAttributeArray.Add(AliasHelper.getFormElementAttribute(m));
            }
            return formElementAttributeArray;
        }
        public static List<M> getFormElementAttributeAliasArray(List<FormElementAttribute> formElementAttributeArray) {
            List<M> mArray = new List<M>();
            foreach(FormElementAttribute formElementAttribute in formElementAttributeArray) {
                mArray.Add(AliasHelper.getFormElementAttributeAlias(formElementAttribute));
            }
            return mArray;
        }



        public static FormElementOption getFormElementOption(N n) {
            if (n == null) { return null; }
            FormElementOption formElementOption = new FormElementOption();
            formElementOption.formElementOptionId = n.n1;
            formElementOption.formElementId = n.n2;
            formElementOption.displayText = AliasHelper.getDisplayText(n.n3);
            formElementOption.value = n.n4;
            return formElementOption;
        }
        public static N getFormElementOptionAlias(FormElementOption formElementOption) {
            if (formElementOption == null) { return null; }
            N n = new N();
            n.n1 = formElementOption.formElementOptionId;
            n.n2 = formElementOption.formElementId;
            n.n3 = AliasHelper.getDisplayTextAlias(formElementOption.displayText);
            n.n4 = formElementOption.value;
            return n;
        }
        public static List<FormElementOption> getFormElementOptionArray(List<N> nArray) {
            List<FormElementOption> formElementOptionArray = new List<FormElementOption>();
            foreach(N n in nArray) {
                formElementOptionArray.Add(AliasHelper.getFormElementOption(n));
            }
            return formElementOptionArray;
        }
        public static List<N> getFormElementOptionAliasArray(List<FormElementOption> formElementOptionArray) {
            List<N> nArray = new List<N>();
            foreach(FormElementOption formElementOption in formElementOptionArray) {
                nArray.Add(AliasHelper.getFormElementOptionAlias(formElementOption));
            }
            return nArray;
        }



        public static FormElementUserDataRelation getFormElementUserDataRelation(O o) {
            if (o == null) { return null; }
            FormElementUserDataRelation formElementUserDataRelation = new FormElementUserDataRelation();
            formElementUserDataRelation.formElementUserDataRelationId = o.o1;
            formElementUserDataRelation.formElementId = o.o2;
            formElementUserDataRelation.userDataId = o.o3;
            return formElementUserDataRelation;
        }
        public static O getFormElementUserDataRelationAlias(FormElementUserDataRelation formElementUserDataRelation) {
            if (formElementUserDataRelation == null) { return null; }
            O o = new O();
            o.o1 = formElementUserDataRelation.formElementUserDataRelationId;
            o.o2 = formElementUserDataRelation.formElementId;
            o.o3 = formElementUserDataRelation.userDataId;
            return o;
        }
        public static List<FormElementUserDataRelation> getFormElementUserDataRelationArray(List<O> oArray) {
            List<FormElementUserDataRelation> formElementUserDataRelationArray = new List<FormElementUserDataRelation>();
            foreach(O o in oArray) {
                formElementUserDataRelationArray.Add(AliasHelper.getFormElementUserDataRelation(o));
            }
            return formElementUserDataRelationArray;
        }
        public static List<O> getFormElementUserDataRelationAliasArray(List<FormElementUserDataRelation> formElementUserDataRelationArray) {
            List<O> oArray = new List<O>();
            foreach(FormElementUserDataRelation formElementUserDataRelation in formElementUserDataRelationArray) {
                oArray.Add(AliasHelper.getFormElementUserDataRelationAlias(formElementUserDataRelation));
            }
            return oArray;
        }



        public static FormFlow getFormFlow(P p) {
            if (p == null) { return null; }
            FormFlow formFlow = new FormFlow();
            formFlow.formFlowId = p.p1;
            formFlow.formId = p.p2;
            formFlow.question = AliasHelper.getQuestion(p.p3);
            formFlow.value = p.p4;
            return formFlow;
        }
        public static P getFormFlowAlias(FormFlow formFlow) {
            if (formFlow == null) { return null; }
            P p = new P();
            p.p1 = formFlow.formFlowId;
            p.p2 = formFlow.formId;
            p.p3 = AliasHelper.getQuestionAlias(formFlow.question);
            p.p4 = formFlow.value;
            return p;
        }
        public static List<FormFlow> getFormFlowArray(List<P> pArray) {
            List<FormFlow> formFlowArray = new List<FormFlow>();
            foreach(P p in pArray) {
                formFlowArray.Add(AliasHelper.getFormFlow(p));
            }
            return formFlowArray;
        }
        public static List<P> getFormFlowAliasArray(List<FormFlow> formFlowArray) {
            List<P> pArray = new List<P>();
            foreach(FormFlow formFlow in formFlowArray) {
                pArray.Add(AliasHelper.getFormFlowAlias(formFlow));
            }
            return pArray;
        }



        public static Question getQuestion(Q q) {
            if (q == null) { return null; }
            Question question = new Question();
            question.questionId = q.q1;
            question.revisionId = q.q2;
            question.name = q.q3;
            question.form = AliasHelper.getForm(q.q4);
            question.automation = AliasHelper.getAutomation(q.q5);
            return question;
        }
        public static Q getQuestionAlias(Question question) {
            if (question == null) { return null; }
            Q q = new Q();
            q.q1 = question.questionId;
            q.q2 = question.revisionId;
            q.q3 = question.name;
            q.q4 = AliasHelper.getFormAlias(question.form);
            q.q5 = AliasHelper.getAutomationAlias(question.automation);
            return q;
        }
        public static List<Question> getQuestionArray(List<Q> qArray) {
            List<Question> questionArray = new List<Question>();
            foreach(Q q in qArray) {
                questionArray.Add(AliasHelper.getQuestion(q));
            }
            return questionArray;
        }
        public static List<Q> getQuestionAliasArray(List<Question> questionArray) {
            List<Q> qArray = new List<Q>();
            foreach(Question question in questionArray) {
                qArray.Add(AliasHelper.getQuestionAlias(question));
            }
            return qArray;
        }



        public static Revision getRevision(R r) {
            if (r == null) { return null; }
            Revision revision = new Revision();
            revision.revisionId = r.r1;
            revision.surveyId = r.r2;
            revision.active = r.r3;
            revision.dateCreated = r.r4;
            revision.revisionNumber = r.r5;
            revision.question = AliasHelper.getQuestion(r.r6);
            return revision;
        }
        public static R getA(Revision revision) {
            if (revision == null) { return null; }
            R r = new R();
            r.r1 = revision.revisionId;
            r.r2 = revision.surveyId;
            r.r3 = revision.active;
            r.r4 = revision.dateCreated;
            r.r5 = revision.revisionNumber;
            r.r6 = AliasHelper.getQuestionAlias(revision.question);
            return r;
        }
        public static List<Revision> getRevisionArray(List<R> rArray) {
            List<Revision> revisionArray = new List<Revision>();
            foreach(R r in rArray) {
                revisionArray.Add(AliasHelper.getRevision(r));
            }
            return revisionArray;
        }
        public static List<R> getRevisionAliasArray(List<Revision> revisionArray) {
            List<R> rArray = new List<R>();
            foreach(Revision revision in revisionArray) {
                rArray.Add(AliasHelper.getA(revision));
            }
            return rArray;
        }



        public static Submission getSubmission(S s) {
            if (s == null) { return null; }
            Submission submission = new Submission();
            submission.submissionId = s.s1;
            submission.revisionId = s.s2;
            submission.dateStarted = s.s3;
            submission.dateCompleted = s.s4;
            submission.submissionAnswers = AliasHelper.getSubmissionAnswerArray(s.s5);
            return submission;
        }
        public static S getSubmissionAlias(Submission submission) {
            if (submission == null) { return null; }
            S s = new S();
            s.s1 = submission.submissionId;
            s.s2 = submission.revisionId;
            s.s3 = submission.dateStarted;
            s.s4 = submission.dateCompleted;
            s.s5 = AliasHelper.getSubmissionAnswerAliasArray(submission.submissionAnswers);
            return s;
        }
        public static List<Submission> getSubmissionArray(List<S> sArray) {
            List<Submission> submissionArray = new List<Submission>();
            foreach(S s in sArray) {
                submissionArray.Add(AliasHelper.getSubmission(s));
            }
            return submissionArray;
        }
        public static List<S> getSubmissionAliasArray(List<Submission> submissionArray) {
            List<S> sArray = new List<S>();
            foreach(Submission submission in submissionArray) {
                sArray.Add(AliasHelper.getSubmissionAlias(submission));
            }
            return sArray;
        }



        public static SubmissionAnswer getSubmissionAnswer(T t) {
            if (t == null) { return null; }
            SubmissionAnswer submissionAnswer = new SubmissionAnswer();
            submissionAnswer.submissionAnswerId = t.t1;
            submissionAnswer.submissionId = t.t2;
            submissionAnswer.questionId = t.t3;
            submissionAnswer.formElementId = t.t4;
            submissionAnswer.value = t.t5;
            return submissionAnswer;
        }
        public static T getSubmissionAnswerAlias(SubmissionAnswer submissionAnswer) {
            if (submissionAnswer == null) { return null; }
            T t = new T();
            t.t1 = submissionAnswer.submissionAnswerId;
            t.t2 = submissionAnswer.submissionId;
            t.t3 = submissionAnswer.questionId;
            t.t4 = submissionAnswer.formElementId;
            t.t5 = submissionAnswer.value;
            return t;
        }
        public static List<SubmissionAnswer> getSubmissionAnswerArray(List<T> tArray) {
            List<SubmissionAnswer> submissionAnswerArray = new List<SubmissionAnswer>();
            foreach(T t in tArray) {
                submissionAnswerArray.Add(AliasHelper.getSubmissionAnswer(t));
            }
            return submissionAnswerArray;
        }
        public static List<T> getSubmissionAnswerAliasArray(List<SubmissionAnswer> submissionAnswerArray) {
            List<T> tArray = new List<T>();
            foreach(SubmissionAnswer submissionAnswer in submissionAnswerArray) {
                tArray.Add(AliasHelper.getSubmissionAnswerAlias(submissionAnswer));
            }
            return tArray;
        }

    }
}