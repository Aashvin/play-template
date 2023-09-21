
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

object viewBook extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[DataModel,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(book: DataModel):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("Find Book")/*4.19*/{_display_(Seq[Any](format.raw/*4.20*/("""
    """),format.raw/*5.5*/("""<h1>Google Book Api Project</h1>
""")))}),format.raw/*6.2*/("""

"""),format.raw/*8.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <p>
            ID: """),_display_(/*16.18*/book/*16.22*/._id),format.raw/*16.26*/(""" """),format.raw/*16.27*/("""<br>
            TITLE: """),_display_(/*17.21*/book/*17.25*/.name),format.raw/*17.30*/(""" """),format.raw/*17.31*/("""<br>
            DESCRIPTION: """),_display_(/*18.27*/book/*18.31*/.description),format.raw/*18.43*/(""" """),format.raw/*18.44*/("""<br>
            NUMBER OF SALES: """),_display_(/*19.31*/book/*19.35*/.numSales),format.raw/*19.44*/(""" """),format.raw/*19.45*/("""<br>
        </p>

    </body>
</html>"""))
      }
    }
  }

  def render(book:DataModel): play.twirl.api.HtmlFormat.Appendable = apply(book)

  def f:((DataModel) => play.twirl.api.HtmlFormat.Appendable) = (book) => apply(book)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/viewBook.scala.html
                  HASH: e70ecd499aea030957d2f7de95252ed8dac0d581
                  MATRIX: 432->1|766->26|877->44|904->46|929->63|967->64|998->69|1061->103|1089->105|1272->261|1285->265|1310->269|1339->270|1391->295|1404->299|1430->304|1459->305|1517->336|1530->340|1563->352|1592->353|1654->388|1667->392|1697->401|1726->402
                  LINES: 17->1|22->2|27->3|28->4|28->4|28->4|29->5|30->6|32->8|40->16|40->16|40->16|40->16|41->17|41->17|41->17|41->17|42->18|42->18|42->18|42->18|43->19|43->19|43->19|43->19
                  -- GENERATED --
              */
          