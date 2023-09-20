
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._
/*1.2*/import models.DataModel
/*2.2*/import helper._

object addBook extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[DataModel],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(personForm: Form[DataModel])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
"""),_display_(/*6.2*/main("Add Person")/*6.20*/{_display_(Seq[Any](format.raw/*6.21*/("""
  """),format.raw/*7.3*/("""<div>
  """),_display_(/*8.4*/helper/*8.10*/.form(action = routes.ApplicationController.addBookForm())/*8.68*/ {_display_(Seq[Any](format.raw/*8.70*/("""
    """),_display_(/*9.6*/helper/*9.12*/.CSRF.formField),format.raw/*9.27*/("""
    """),_display_(/*10.6*/helper/*10.12*/.inputText(personForm("_id"))),format.raw/*10.41*/("""
    """),_display_(/*11.6*/helper/*11.12*/.inputText(personForm("name"))),format.raw/*11.42*/("""
    """),_display_(/*12.6*/helper/*12.12*/.inputText(personForm("description"))),format.raw/*12.49*/("""
    """),_display_(/*13.6*/helper/*13.12*/.inputText(personForm("numSales"))),format.raw/*13.46*/("""
    """),format.raw/*14.5*/("""<input class="submitButton" type="submit" value="Submit">
    """)))}),format.raw/*15.6*/("""
  """),format.raw/*16.3*/("""</div>
""")))}))
      }
    }
  }

  def render(personForm:Form[DataModel],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(personForm)(request,messages)

  def f:((Form[DataModel]) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (personForm) => (request,messages) => apply(personForm)(request,messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/addBook.scala.html
                  HASH: 3aa6245ae0ba149fb09fb7d3218b54f7acb5ab60
                  MATRIX: 432->1|463->26|817->44|993->127|1020->129|1046->147|1084->148|1113->151|1147->160|1161->166|1227->224|1266->226|1297->232|1311->238|1346->253|1378->259|1393->265|1443->294|1475->300|1490->306|1541->336|1573->342|1588->348|1646->385|1678->391|1693->397|1748->431|1780->436|1873->499|1903->502
                  LINES: 17->1|18->2|23->4|28->5|29->6|29->6|29->6|30->7|31->8|31->8|31->8|31->8|32->9|32->9|32->9|33->10|33->10|33->10|34->11|34->11|34->11|35->12|35->12|35->12|36->13|36->13|36->13|37->14|38->15|39->16
                  -- GENERATED --
              */
          