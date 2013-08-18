<div id="Header">
    <img src="Site_Files/ReceiptRewardsBanner.png"></img>
</div>
<div id="Nav">
    <div style="display: inline-block; display: -moz-inline-stack;  width: 740px; zoom: 1; *display: inline;">
        <%
            int tempInt = 0;

            // Code Type
            if (Request.QueryString["codeTypeId"] != null && int.TryParse(Request.QueryString["codeTypeId"], out tempInt)) {
                Response.Write(ReceiptReward.CodeTypeImpl.getBreadcrumbs(int.Parse(Request.QueryString["codeTypeId"])));

            // Code
            } else if (Request.QueryString["codeId"] != null && int.TryParse(Request.QueryString["codeId"], out tempInt)) {
                Response.Write(ReceiptReward.CodeImpl.getBreadcrumbs(int.Parse(Request.QueryString["codeId"])));

            // Company
            } else if (Request.QueryString["companyId"] != null && int.TryParse(Request.QueryString["companyId"], out tempInt)) {
                Response.Write(ReceiptReward.CompanyImpl.getBreadcrumbs(int.Parse(Request.QueryString["companyId"])));

            // Survey
            } else if (Request.QueryString["surveyId"] != null && int.TryParse(Request.QueryString["surveyId"], out tempInt)) {
                Response.Write(ReceiptReward.SurveyImpl.getBreadcrumbs(int.Parse(Request.QueryString["surveyId"])));

            // Command Element
            } else if (Request.QueryString["commandElementId"] != null && int.TryParse(Request.QueryString["commandElementId"], out tempInt) && Request.QueryString["revisionId"] != null && int.TryParse(Request.QueryString["revisionId"], out tempInt)) {
                Response.Write(ReceiptReward.CommandElementImpl.getBreadcrumbs(int.Parse(Request.QueryString["commandElementId"]), int.Parse(Request.QueryString["revisionId"])));

            // Command Identifier
            } else if (Request.QueryString["commandIdentifierId"] != null && int.TryParse(Request.QueryString["commandIdentifierId"], out tempInt) && Request.QueryString["revisionId"] != null && int.TryParse(Request.QueryString["revisionId"], out tempInt)) {
                Response.Write(ReceiptReward.CommandIdentifierImpl.getBreadcrumbs(int.Parse(Request.QueryString["commandIdentifierId"]), int.Parse(Request.QueryString["revisionId"])));

            // Form Element Attribute
            } else if (Request.QueryString["formElementAttributeId"] != null && int.TryParse(Request.QueryString["formElementAttributeId"], out tempInt) && Request.QueryString["revisionId"] != null && int.TryParse(Request.QueryString["revisionId"], out tempInt)) {
                Response.Write(ReceiptReward.FormElementAttributeImpl.getBreadcrumbs(int.Parse(Request.QueryString["formElementAttributeId"]), int.Parse(Request.QueryString["revisionId"])));

            // Form Element Option
            } else if (Request.QueryString["formElementOptionId"] != null && int.TryParse(Request.QueryString["formElementOptionId"], out tempInt) && Request.QueryString["revisionId"] != null && int.TryParse(Request.QueryString["revisionId"], out tempInt)) {
                Response.Write(ReceiptReward.FormElementOptionImpl.getBreadcrumbs(int.Parse(Request.QueryString["formElementOptionId"]), int.Parse(Request.QueryString["revisionId"])));

            // Form Element
            } else if (Request.QueryString["questionId"] != null && int.TryParse(Request.QueryString["questionId"], out tempInt) && Request.QueryString["revisionId"] != null && int.TryParse(Request.QueryString["revisionId"], out tempInt)) {
                Response.Write(ReceiptReward.QuestionImpl.getBreadcrumbs(int.Parse(Request.QueryString["questionId"]), int.Parse(Request.QueryString["revisionId"])));

            // Question
            } else if (Request.QueryString["revisionId"] != null && int.TryParse(Request.QueryString["revisionId"], out tempInt)) {
                Response.Write(ReceiptReward.RevisionImpl.getBreadcrumbs(int.Parse(Request.QueryString["revisionId"])));

            // Revision
            } else if (Request.QueryString["revisionId"] != null && int.TryParse(Request.QueryString["revisionId"], out tempInt)) {
                Response.Write(ReceiptReward.RevisionImpl.getBreadcrumbs(int.Parse(Request.QueryString["revisionId"])));
        
            } else {
                Response.Write("<a href='Index.aspx'>Home</a>");
            }
        %>
    </div>
    <div id="userStatus" style="display: inline-block; display: -moz-inline-stack;  width: 200px; text-align: right; zoom: 1; *display: inline;">

    </div>
</div>