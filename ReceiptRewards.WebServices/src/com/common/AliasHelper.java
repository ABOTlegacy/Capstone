package com.common;

import com.aliasmodels.A;
import com.aliasmodels.B;
import com.aliasmodels.C;
import com.aliasmodels.D;
import com.aliasmodels.E;
import com.aliasmodels.F;
import com.aliasmodels.G;
import com.aliasmodels.H;
import com.aliasmodels.I;
import com.aliasmodels.J;
import com.aliasmodels.K;
import com.aliasmodels.L;
import com.aliasmodels.M;
import com.aliasmodels.N;
import com.aliasmodels.O;
import com.aliasmodels.P;
import com.aliasmodels.Q;
import com.aliasmodels.R;
import com.aliasmodels.S;
import com.aliasmodels.T;
import com.aliasmodels.U;
import com.model.Automation;
import com.model.AutomationFlow;
import com.model.Code;
import com.model.CodeType;
import com.model.CommandElement;
import com.model.CommandFormElementRelation;
import com.model.CommandIdentifier;
import com.model.Company;
import com.model.DisplayText;
import com.model.DisplayTextTranslation;
import com.model.Form;
import com.model.FormElement;
import com.model.FormElementAttribute;
import com.model.FormElementOption;
import com.model.FormElementUserDataRelation;
import com.model.FormFlow;
import com.model.Question;
import com.model.Revision;
import com.model.Submission;
import com.model.SubmissionAnswer;
import com.model.Survey;
import java.util.ArrayList;
import java.util.List;

public class AliasHelper {
    
    
    
    public static Automation getAutomation(A a) {
        if(a == null) { return null; }
        Automation automation = new Automation();
        automation.setAutomationId(a.getA1());
        automation.setCommandElements(AliasHelper.getCommandElementArray(a.getA2()));
        automation.setAutomationFlows(AliasHelper.getAutomationFlowArray(a.getA3()));
        return automation;
    }
    public static A getAutomationAlias(Automation automation) {
        if(automation == null) { return null; }
        A a = new A();
        a.setA1(automation.getAutomationId());
        a.setA2(AliasHelper.getCommandElementAliasArray(automation.getCommandElements()));
        a.setA3(AliasHelper.getAutomationFlowAliasArray(automation.getAutomationFlows()));
        return a;
    }
    public static List<Automation> getAutomationArray(List<A> aArray) {
        List<Automation> automationArray = new ArrayList<Automation>();
        for(A a : aArray) {
            automationArray.add(AliasHelper.getAutomation(a));
        }
        return automationArray;
    }
    public static List<A> getAutomationAliasArray(List<Automation> automationArray) {
        List<A> aArray = new ArrayList<A>();
        for(Automation automation : automationArray) {
            aArray.add(AliasHelper.getAutomationAlias(automation));
        }
        return aArray;
    }
    
    
    
    public static AutomationFlow getAutomationFlow(B b) {
        if(b == null) { return null; }
        AutomationFlow automationFlow = new AutomationFlow();
        automationFlow.setAutomationFlowId(b.getB1());
        automationFlow.setAutomationId(b.getB2());
        automationFlow.setQuestion(AliasHelper.getQuestion(b.getB3()));
        automationFlow.setWeight(b.getB4());
        return automationFlow;
    }
    public static B getAutomationFlowAlias(AutomationFlow automationFlow) {
        if(automationFlow == null) { return null; }
        B b = new B();
        b.setB1(automationFlow.getAutomationFlowId());
        b.setB2(automationFlow.getAutomationId());
        b.setB3(AliasHelper.getQuestionAlias(automationFlow.getQuestion()));
        b.setB4(automationFlow.getWeight());
        return b;
    }
    public static List<AutomationFlow> getAutomationFlowArray(List<B> bArray) {
        List<AutomationFlow> automationFlowArray = new ArrayList<AutomationFlow>();
        for(B b : bArray) {
            automationFlowArray.add(AliasHelper.getAutomationFlow(b));
        }
        return automationFlowArray;
    }
    public static List<B> getAutomationFlowAliasArray(List<AutomationFlow> automationFlowArray) {
        List<B> bArray = new ArrayList<B>();
        for(AutomationFlow automationFlow : automationFlowArray) {
            bArray.add(AliasHelper.getAutomationFlowAlias(automationFlow));
        }
        return bArray;
    }
    
    
    
    public static Company getCompany(H h) {
        if(h == null) { return null; }
        Company company = new Company();
        company.setCompanyId(h.getH1());
        company.setName(h.getH2());
        company.setSurveys(AliasHelper.getSurveyArray(h.getH3()));
        return company;
    }
    public static H getCompanyAlias(Company company) {
        if(company == null) { return null; }
        H h = new H();
        h.setH1(company.getCompanyId());
        h.setH2(company.getName());
        h.setH3(AliasHelper.getSurveyAliasArray(company.getSurveys()));
        return h;
    }
    public static List<Company> getCompanyArray(List<H> hArray) {
        List<Company> companyArray = new ArrayList<Company>();
        for(H h : hArray) {
            companyArray.add(AliasHelper.getCompany(h));
        }
        return companyArray;
    }
    public static List<H> getCompanyAliasArray(List<Company> companyArray) {
        List<H> hArray = new ArrayList<H>();
        for(Company company : companyArray) {
            hArray.add(AliasHelper.getCompanyAlias(company));
        }
        return hArray;
    }
    
    
    
    public static Survey getSurvey(U u) {
        if(u == null) { return null; }
        Survey survey = new Survey();
        survey.setSurveyId(u.getU1());
        survey.setCompanyId(u.getU2());
        survey.setName(u.getU3());
        survey.setRevisions(AliasHelper.getRevisionArray(u.getU4()));
        return survey;
    }
    public static U getSurveyAlias(Survey survey) {
        if(survey == null) { return null; }
        U u = new U();
        u.setU1(survey.getSurveyId());
        u.setU2(survey.getCompanyId());
        u.setU3(survey.getName());
        u.setU4(AliasHelper.getRevisionAliasArray(survey.getRevisions()));
        return u;
    }
    public static List<Survey> getSurveyArray(List<U> uArray) {
        List<Survey> surveyArray = new ArrayList<Survey>();
        for(U u : uArray) {
            surveyArray.add(AliasHelper.getSurvey(u));
        }
        return surveyArray;
    }
    public static List<U> getSurveyAliasArray(List<Survey> surveyArray) {
        List<U> uArray = new ArrayList<U>();
        for(Survey survey : surveyArray) {
            uArray.add(AliasHelper.getSurveyAlias(survey));
        }
        return uArray;
    }
    
    
    
    public static Code getCode(C c) {
        if(c == null) { return null; }
        Code code = new Code();
        code.setCodeId(c.getC1());
        code.setCode(c.getC2());
        code.setName(c.getC3());
        code.setDescription(c.getC4());
        code.setCodeType(AliasHelper.getCodeType(c.getC5()));
        return code;
    }
    public static C getCodeAlias(Code code) {
        if(code == null) { return null; }
        C c = new C();
        c.setC1(code.getCodeId());
        c.setC2(code.getCode());
        c.setC3(code.getName());
        c.setC4(code.getDescription());
        c.setC5(AliasHelper.getCodeTypeAlias(code.getCodeType()));
        return c;
    }
    public static List<Code> getCodeArray(List<C> cArray) {
        List<Code> codeArray = new ArrayList<Code>();
        for(C c : cArray) {
            codeArray.add(AliasHelper.getCode(c));
        }
        return codeArray;
    }
    public static List<C> getCodeAliasArray(List<Code> codeArray) {
        List<C> cArray = new ArrayList<C>();
        for(Code code : codeArray) {
            cArray.add(AliasHelper.getCodeAlias(code));
        }
        return cArray;
    }
    
    
    
    public static CodeType getCodeType(D d) {
        if(d == null) { return null; }
        CodeType codeType = new CodeType();
        codeType.setCodeTypeId(d.getD1());
        codeType.setType(d.getD2());
        codeType.setDescription(d.getD3());
        return codeType;
    }
    public static D getCodeTypeAlias(CodeType codeType) {
        if(codeType == null) { return null; }
        D d = new D();
        d.setD1(codeType.getCodeTypeId());
        d.setD2(codeType.getType());
        d.setD3(codeType.getDescription());
        return d;
    }
    public static List<CodeType> getCodeTypeArray(List<D> dArray) {
        List<CodeType> codeTypeArray = new ArrayList<CodeType>();
        for(D d : dArray) {
            codeTypeArray.add(AliasHelper.getCodeType(d));
        }
        return codeTypeArray;
    }
    public static List<D> getCodeTypeAliasArray(List<CodeType> codeTypeArray) {
        List<D> dArray = new ArrayList<D>();
        for(CodeType codeType : codeTypeArray) {
            dArray.add(AliasHelper.getCodeTypeAlias(codeType));
        }
        return dArray;
    }
    
    
    
    public static CommandElement getCommandElement(E e) {
        if(e == null) { return null; }
        CommandElement commandElement = new CommandElement();
        commandElement.setCommandElementId(e.getE1());
        commandElement.setCode(AliasHelper.getCode(e.getE2()));
        commandElement.setCommandIdentifiers(AliasHelper.getCommandIdentifierArray(e.getE3()));
        commandElement.setFormElements(AliasHelper.getCommandFormElementRelationArray(e.getE4()));
        commandElement.setTestData(e.getE5());
        return commandElement;
    }
    public static E getCommandElementAlias(CommandElement commandElement) {
        if(commandElement == null) { return null; }
        E e = new E();
        e.setE1(commandElement.getCommandElementId());
        e.setE2(AliasHelper.getCodeAlias(commandElement.getCode()));
        e.setE3(AliasHelper.getCommandIdentifierAliasArray(commandElement.getCommandIdentifiers()));
        e.setE4(AliasHelper.getCommandFormElementRelationAliasArray(commandElement.getFormElements()));
        e.setE5(commandElement.getTestData());
        return e;
    }
    public static List<CommandElement> getCommandElementArray(List<E> eArray) {
        List<CommandElement> commandElementArray = new ArrayList<CommandElement>();
        for(E e : eArray) {
            commandElementArray.add(AliasHelper.getCommandElement(e));
        }
        return commandElementArray;
    }
    public static List<E> getCommandElementAliasArray(List<CommandElement> commandElementArray) {
        List<E> eArray = new ArrayList<E>();
        for(CommandElement commandElement : commandElementArray) {
            eArray.add(AliasHelper.getCommandElementAlias(commandElement));
        }
        return eArray;
    }
    
    
    
    public static CommandFormElementRelation getCommandFormElementRelation(F f) {
        if(f == null) { return null; }
        CommandFormElementRelation commandFormElementRelation = new CommandFormElementRelation();
        commandFormElementRelation.setCommandFormElementRelationId(f.getF1());
        commandFormElementRelation.setCommandElementId(f.getF2());
        commandFormElementRelation.setFormElementId(f.getF3());
        return commandFormElementRelation;
    }
    public static F getCommandFormElementRelationAlias(CommandFormElementRelation commandFormElementRelation) {
        if(commandFormElementRelation == null) { return null; }
        F f = new F();
        f.setF1(commandFormElementRelation.getCommandFormElementRelationId());
        f.setF2(commandFormElementRelation.getCommandElementId());
        f.setF3(commandFormElementRelation.getFormElementId());
        return f;
    }
    public static List<CommandFormElementRelation> getCommandFormElementRelationArray(List<F> fArray) {
        List<CommandFormElementRelation> commandFormElementRelationArray = new ArrayList<CommandFormElementRelation>();
        for(F f : fArray) {
            commandFormElementRelationArray.add(AliasHelper.getCommandFormElementRelation(f));
        }
        return commandFormElementRelationArray;
    }
    public static List<F> getCommandFormElementRelationAliasArray(List<CommandFormElementRelation> commandFormElementRelationArray) {
        List<F> fArray = new ArrayList<F>();
        for(CommandFormElementRelation commandFormElementRelation : commandFormElementRelationArray) {
            fArray.add(AliasHelper.getCommandFormElementRelationAlias(commandFormElementRelation));
        }
        return fArray;
    }
    
    
    
    public static CommandIdentifier getCommandIdentifier(G g) {
        if(g == null) { return null; }
        CommandIdentifier commandIdentifier = new CommandIdentifier();
        commandIdentifier.setCommandIdentifierId(g.getG1());
        commandIdentifier.setCommandElementId(g.getG2());
        commandIdentifier.setCode(AliasHelper.getCode(g.getG3()));
        commandIdentifier.setValue(g.getG4());
        return commandIdentifier;
    }
    public static G getCommandIdentifierAlias(CommandIdentifier commandIdentifier) {
        if(commandIdentifier == null) { return null; }
        G g = new G();
        g.setG1(commandIdentifier.getCommandIdentifierId());
        g.setG2(commandIdentifier.getCommandElementId());
        g.setG3(AliasHelper.getCodeAlias(commandIdentifier.getCode()));
        g.setG4(commandIdentifier.getValue());
        return g;
    }
    public static List<CommandIdentifier> getCommandIdentifierArray(List<G> gArray) {
        List<CommandIdentifier> commandIdentifierArray = new ArrayList<CommandIdentifier>();
        for(G g : gArray) {
            commandIdentifierArray.add(AliasHelper.getCommandIdentifier(g));
        }
        return commandIdentifierArray;
    }
    public static List<G> getCommandIdentifierAliasArray(List<CommandIdentifier> commandIdentifierArray) {
        List<G> gArray = new ArrayList<G>();
        for(CommandIdentifier commandIdentifier : commandIdentifierArray) {
            gArray.add(AliasHelper.getCommandIdentifierAlias(commandIdentifier));
        }
        return gArray;
    }
    
    
    
    public static DisplayText getDisplayText(I i) {
        if(i == null) { return null; }
        DisplayText displayText = new DisplayText();
        displayText.setDisplayTextId(i.getI1());
        displayText.setDisplayTextTranslation(i.getI2());
        displayText.setDisplayTextTranslations(AliasHelper.getDisplayTextTranslationArray(i.getI3()));
        return displayText;
    }
    public static I getDisplayTextAlias(DisplayText displayText) {
        if(displayText == null) { return null; }
        I i = new I();
        i.setI1(displayText.getDisplayTextId());
        i.setI2(displayText.getDisplayTextTranslation());
        i.setI3(AliasHelper.getDisplayTextTranslationAliasArray(displayText.getDisplayTextTranslations()));
        return i;
    }
    public static List<DisplayText> getDisplayTextArray(List<I> iArray) {
        List<DisplayText> displayTextArray = new ArrayList<DisplayText>();
        for(I i : iArray) {
            displayTextArray.add(AliasHelper.getDisplayText(i));
        }
        return displayTextArray;
    }
    public static List<I> getDisplayTextAliasArray(List<DisplayText> displayTextArray) {
        List<I> iArray = new ArrayList<I>();
        for(DisplayText displayText : displayTextArray) {
            iArray.add(AliasHelper.getDisplayTextAlias(displayText));
        }
        return iArray;
    }
    
    
    
    public static DisplayTextTranslation getDisplayTextTranslation(J j) {
        if(j == null) { return null; }
        DisplayTextTranslation displayTextTranslation = new DisplayTextTranslation();
        displayTextTranslation.setDisplayTextTranslationId(j.getJ1());
        displayTextTranslation.setDisplayTextId(j.getJ2());
        displayTextTranslation.setCode(AliasHelper.getCode(j.getJ3()));
        displayTextTranslation.setValue(j.getJ4());
        return displayTextTranslation;
    }
    public static J getDisplayTextTranslationAlias(DisplayTextTranslation displayTextTranslation) {
        if(displayTextTranslation == null) { return null; }
        J j = new J();
        j.setJ1(displayTextTranslation.getDisplayTextTranslationId());
        j.setJ2(displayTextTranslation.getDisplayTextId());
        j.setJ3(AliasHelper.getCodeAlias(displayTextTranslation.getCode()));
        j.setJ4(displayTextTranslation.getValue());
        return j;
    }
    public static List<DisplayTextTranslation> getDisplayTextTranslationArray(List<J> jArray) {
        List<DisplayTextTranslation> displayTextTranslationArray = new ArrayList<DisplayTextTranslation>();
        for(J j : jArray) {
            displayTextTranslationArray.add(AliasHelper.getDisplayTextTranslation(j));
        }
        return displayTextTranslationArray;
    }
    public static List<J> getDisplayTextTranslationAliasArray(List<DisplayTextTranslation> displayTextTranslationArray) {
        List<J> jArray = new ArrayList<J>();
        for(DisplayTextTranslation displayTextTranslation : displayTextTranslationArray) {
            jArray.add(AliasHelper.getDisplayTextTranslationAlias(displayTextTranslation));
        }
        return jArray;
    }
    
    
    
    public static Form getForm(K k) {
        if(k == null) { return null; }
        Form form = new Form();
        form.setFormId(k.getK1());
        form.setFormElements(AliasHelper.getFormElementArray(k.getK2()));
        form.setFormFlows(AliasHelper.getFormFlowArray(k.getK3()));
        return form;
    }
    public static K getFormAlias(Form form) {
        if(form == null) { return null; }
        K k = new K();
        k.setK1(form.getFormId());
        k.setK2(AliasHelper.getFormElementAliasArray(form.getFormElements()));
        k.setK3(AliasHelper.getFormFlowAliasArray(form.getFormFlows()));
        return k;
    }
    public static List<Form> getFormArray(List<K> kArray) {
        List<Form> formArray = new ArrayList<Form>();
        for(K k : kArray) {
            formArray.add(AliasHelper.getForm(k));
        }
        return formArray;
    }
    public static List<K> getFormAliasArray(List<Form> formArray) {
        List<K> kArray = new ArrayList<K>();
        for(Form form : formArray) {
            kArray.add(AliasHelper.getFormAlias(form));
        }
        return kArray;
    }
    
    
    
    public static FormElement getFormElement(L l) {
        if(l == null) { return null; }
        FormElement formElement = new FormElement();
        formElement.setFormElementId(l.getL1());
        formElement.setCode(AliasHelper.getCode(l.getL2()));
        formElement.setFormElementOptions(AliasHelper.getFormElementOptionArray(l.getL3()));
        formElement.setFormElementAttributes(AliasHelper.getFormElementAttributeArray(l.getL4()));
        return formElement;
    }
    public static L getFormElementAlias(FormElement formElement) {
        if(formElement == null) { return null; }
        L l = new L();
        l.setL1(formElement.getFormElementId());
        l.setL2(AliasHelper.getCodeAlias(formElement.getCode()));
        l.setL3(AliasHelper.getFormElementOptionAliasArray(formElement.getFormElementOptions()));
        l.setL4(AliasHelper.getFormElementAttributeAliasArray(formElement.getFormElementAttributes()));
        return l;
    }
    public static List<FormElement> getFormElementArray(List<L> lArray) {
        List<FormElement> formElementArray = new ArrayList<FormElement>();
        for(L l : lArray) {
            formElementArray.add(AliasHelper.getFormElement(l));
        }
        return formElementArray;
    }
    public static List<L> getFormElementAliasArray(List<FormElement> formElementArray) {
        List<L> lArray = new ArrayList<L>();
        for(FormElement formElement : formElementArray) {
            lArray.add(AliasHelper.getFormElementAlias(formElement));
        }
        return lArray;
    }
    
    
    
    public static FormElementAttribute getFormElementAttribute(M m) {
        if(m == null) { return null; }
        FormElementAttribute formElementAttribute = new FormElementAttribute();
        formElementAttribute.setFormElementAttributeId(m.getM1());
        formElementAttribute.setFormElementId(m.getM2());
        formElementAttribute.setCode(AliasHelper.getCode(m.getM3()));
        formElementAttribute.setValue(m.getM4());
        return formElementAttribute;
    }
    public static M getFormElementAttributeAlias(FormElementAttribute formElementAttribute) {
        if(formElementAttribute == null) { return null; }
        M m = new M();
        m.setM1(formElementAttribute.getFormElementAttributeId());
        m.setM2(formElementAttribute.getFormElementId());
        m.setM3(AliasHelper.getCodeAlias(formElementAttribute.getCode()));
        m.setM4(formElementAttribute.getValue());
        return m;
    }
    public static List<FormElementAttribute> getFormElementAttributeArray(List<M> mArray) {
        List<FormElementAttribute> formElementAttributeArray = new ArrayList<FormElementAttribute>();
        for(M m : mArray) {
            formElementAttributeArray.add(AliasHelper.getFormElementAttribute(m));
        }
        return formElementAttributeArray;
    }
    public static List<M> getFormElementAttributeAliasArray(List<FormElementAttribute> formElementAttributeArray) {
        List<M> mArray = new ArrayList<M>();
        for(FormElementAttribute formElementAttribute : formElementAttributeArray) {
            mArray.add(AliasHelper.getFormElementAttributeAlias(formElementAttribute));
        }
        return mArray;
    }
    
    
    
    public static FormElementOption getFormElementOption(N n) {
        if(n == null) { return null; }
        FormElementOption formElementOption = new FormElementOption();
        formElementOption.setFormElementOptionId(n.getN1());
        formElementOption.setFormElementId(n.getN2());
        formElementOption.setDisplayText(AliasHelper.getDisplayText(n.getN3()));
        formElementOption.setValue(n.getN4());
        return formElementOption;
    }
    public static N getFormElementOptionAlias(FormElementOption formElementOption) {
        if(formElementOption == null) { return null; }
        N n = new N();
        n.setN1(formElementOption.getFormElementOptionId());
        n.setN2(formElementOption.getFormElementId());
        n.setN3(AliasHelper.getDisplayTextAlias(formElementOption.getDisplayText()));
        n.setN4(formElementOption.getValue());
        return n;
    }
    public static List<FormElementOption> getFormElementOptionArray(List<N> nArray) {
        List<FormElementOption> formElementOptionArray = new ArrayList<FormElementOption>();
        for(N n : nArray) {
            formElementOptionArray.add(AliasHelper.getFormElementOption(n));
        }
        return formElementOptionArray;
    }
    public static List<N> getFormElementOptionAliasArray(List<FormElementOption> formElementOptionArray) {
        List<N> nArray = new ArrayList<N>();
        for(FormElementOption formElementOption : formElementOptionArray) {
            nArray.add(AliasHelper.getFormElementOptionAlias(formElementOption));
        }
        return nArray;
    }
    
    
    
    public static FormElementUserDataRelation getFormElementUserDataRelation(O o) {
        if(o == null) { return null; }
        FormElementUserDataRelation formElementUserDataRelation = new FormElementUserDataRelation();
        formElementUserDataRelation.setFormElementUserDataRelationId(o.getO1());
        formElementUserDataRelation.setFormElementId(o.getO2());
        formElementUserDataRelation.setUserDataId(o.getO3());
        return formElementUserDataRelation;
    }
    public static O getFormElementUserDataRelationAlias(FormElementUserDataRelation formElementUserDataRelation) {
        if(formElementUserDataRelation == null) { return null; }
        O o = new O();
        o.setO1(formElementUserDataRelation.getFormElementUserDataRelationId());
        o.setO2(formElementUserDataRelation.getFormElementId());
        o.setO3(formElementUserDataRelation.getUserDataId());
        return o;
    }
    public static List<FormElementUserDataRelation> getFormElementUserDataRelationArray(List<O> oArray) {
        List<FormElementUserDataRelation> formElementUserDataRelationArray = new ArrayList<FormElementUserDataRelation>();
        for(O o : oArray) {
            formElementUserDataRelationArray.add(AliasHelper.getFormElementUserDataRelation(o));
        }
        return formElementUserDataRelationArray;
    }
    public static List<O> getFormElementUserDataRelationAliasArray(List<FormElementUserDataRelation> formElementUserDataRelationArray) {
        List<O> oArray = new ArrayList<O>();
        for(FormElementUserDataRelation formElementUserDataRelation : formElementUserDataRelationArray) {
            oArray.add(AliasHelper.getFormElementUserDataRelationAlias(formElementUserDataRelation));
        }
        return oArray;
    }
    
    
    
    public static FormFlow getFormFlow(P p) {
        if(p == null) { return null; }
        FormFlow formFlow = new FormFlow();
        formFlow.setFormFlowId(p.getP1());
        formFlow.setFormId(p.getP2());
        formFlow.setQuestion(AliasHelper.getQuestion(p.getP3()));
        formFlow.setValue(p.getP4());
        return formFlow;
    }
    public static P getFormFlowAlias(FormFlow formFlow) {
        if(formFlow == null) { return null; }
        P p = new P();
        p.setP1(formFlow.getFormFlowId());
        p.setP2(formFlow.getFormId());
        p.setP3(AliasHelper.getQuestionAlias(formFlow.getQuestion()));
        p.setP4(formFlow.getValue());
        return p;
    }
    public static List<FormFlow> getFormFlowArray(List<P> pArray) {
        List<FormFlow> formFlowArray = new ArrayList<FormFlow>();
        for(P p : pArray) {
            formFlowArray.add(AliasHelper.getFormFlow(p));
        }
        return formFlowArray;
    }
    public static List<P> getFormFlowAliasArray(List<FormFlow> formFlowArray) {
        List<P> pArray = new ArrayList<P>();
        for(FormFlow formFlow : formFlowArray) {
            pArray.add(AliasHelper.getFormFlowAlias(formFlow));
        }
        return pArray;
    }
    
    
    
    public static Question getQuestion(Q q) {
        if(q == null) { return null; }
        Question question = new Question();
        question.setQuestionId(q.getQ1());
        question.setRevisionId(q.getQ2());
        question.setName(q.getQ3());
        question.setForm(AliasHelper.getForm(q.getQ4()));
        question.setAutomation(AliasHelper.getAutomation(q.getQ5()));
        return question;
    }
    public static Q getQuestionAlias(Question question) {
        if(question == null) { return null; }
        Q q = new Q();
        q.setQ1(question.getQuestionId());
        q.setQ2(question.getRevisionId());
        q.setQ3(question.getName());
        q.setQ4(AliasHelper.getFormAlias(question.getForm()));
        q.setQ5(AliasHelper.getAutomationAlias(question.getAutomation()));
        return q;
    }
    public static List<Question> getQuestionArray(List<Q> qArray) {
        List<Question> questionArray = new ArrayList<Question>();
        for(Q q : qArray) {
            questionArray.add(AliasHelper.getQuestion(q));
        }
        return questionArray;
    }
    public static List<Q> getQuestionAliasArray(List<Question> questionArray) {
        List<Q> qArray = new ArrayList<Q>();
        for(Question question : questionArray) {
            qArray.add(AliasHelper.getQuestionAlias(question));
        }
        return qArray;
    }
    
    
    
    public static Revision getRevision(R r) {
        if(r == null) { return null; }
        Revision revision = new Revision();
        revision.setRevisionId(r.getR1());
        revision.setSurveyId(r.getR2());
        revision.setActive(r.isR3());
        revision.setDateCreated(r.getR4());
        revision.setRevisionNumber(r.getR5());
        revision.setQuestion(AliasHelper.getQuestion(r.getR6()));
        return revision;
    }
    public static R getRevisionAlias(Revision revision) {
        if(revision == null) { return null; }
        R r = new R();
        r.setR1(revision.getRevisionId());
        r.setR2(revision.getSurveyId());
        r.setR3(revision.isActive());
        r.setR4(revision.getDateCreated());
        r.setR5(revision.getRevisionNumber());
        r.setR6(AliasHelper.getQuestionAlias(revision.getQuestion()));
        return r;
    }
    public static List<Revision> getRevisionArray(List<R> rArray) {
        List<Revision> revisionArray = new ArrayList<Revision>();
        for(R r : rArray) {
            revisionArray.add(AliasHelper.getRevision(r));
        }
        return revisionArray;
    }
    public static List<R> getRevisionAliasArray(List<Revision> revisionArray) {
        List<R> rArray = new ArrayList<R>();
        for(Revision revision : revisionArray) {
            rArray.add(AliasHelper.getRevisionAlias(revision));
        }
        return rArray;
    }
    
    
    
    public static Submission getSubmission(S s) {
        if(s == null) { return null; }
        Submission submission = new Submission();
        submission.setSubmissionId(s.getS1());
        submission.setRevisionId(s.getS2());
        submission.setDateStarted(s.getS3());
        submission.setDateCompleted(s.getS4());
        submission.setSubmissionAnswers(AliasHelper.getSubmissionAnswerArray(s.getS5()));
        return submission;
    }
    public static S getSubmissionAlias(Submission submission) {
        if(submission == null) { return null; }
        S s = new S();
        s.setS1(submission.getSubmissionId());
        s.setS2(submission.getRevisionId());
        s.setS3(submission.getDateStarted());
        s.setS4(submission.getDateCompleted());
        s.setS5(AliasHelper.getSubmissionAnswerAliasArray(submission.getSubmissionAnswers()));
        return s;
    }
    public static List<Submission> getSubmissionArray(List<S> sArray) {
        List<Submission> submissionArray = new ArrayList<Submission>();
        for(S s : sArray) {
            submissionArray.add(AliasHelper.getSubmission(s));
        }
        return submissionArray;
    }
    public static List<S> getSubmissionAliasArray(List<Submission> submissionArray) {
        List<S> sArray = new ArrayList<S>();
        for(Submission submission : submissionArray) {
            sArray.add(AliasHelper.getSubmissionAlias(submission));
        }
        return sArray;
    }
    
    
    
    public static SubmissionAnswer getSubmissionAnswer(T t) {
        if(t == null) { return null; }
        SubmissionAnswer submissionAnswer = new SubmissionAnswer();
        submissionAnswer.setSubmissionAnswerId(t.getT1());
        submissionAnswer.setSubmissionId(t.getT2());
        submissionAnswer.setQuestionId(t.getT3());
        submissionAnswer.setFormElementId(t.getT4());
        submissionAnswer.setValue(t.getT5());
        return submissionAnswer;
    }
    public static T getSubmissionAnswerAlias(SubmissionAnswer submissionAnswer) {
        if(submissionAnswer == null) { return null; }
        T t = new T();
        t.setT1(submissionAnswer.getSubmissionAnswerId());
        t.setT2(submissionAnswer.getSubmissionId());
        t.setT3(submissionAnswer.getQuestionId());
        t.setT4(submissionAnswer.getFormElementId());
        t.setT5(submissionAnswer.getValue());
        return t;
    }
    public static List<SubmissionAnswer> getSubmissionAnswerArray(List<T> tArray) {
        List<SubmissionAnswer> submissionAnswerArray = new ArrayList<SubmissionAnswer>();
        for(T t : tArray) {
            submissionAnswerArray.add(AliasHelper.getSubmissionAnswer(t));
        }
        return submissionAnswerArray;
    }
    public static List<T> getSubmissionAnswerAliasArray(List<SubmissionAnswer> submissionAnswerArray) {
        List<T> tArray = new ArrayList<T>();
        for(SubmissionAnswer submissionAnswer : submissionAnswerArray) {
            tArray.add(AliasHelper.getSubmissionAnswerAlias(submissionAnswer));
        }
        return tArray;
    }
}