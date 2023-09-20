
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.1*/("""
"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(/*12.70*/routes/*12.76*/.Assets.versioned("/stylesheets/main.css")),format.raw/*12.118*/("""">
        """),format.raw/*13.62*/("""
        """),format.raw/*14.9*/("""<title class="title-thing">"""),_display_(/*14.37*/title),format.raw/*14.42*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*15.54*/routes/*15.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*15.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*16.59*/routes/*16.65*/.Assets.versioned("images/favicon.png")),format.raw/*16.104*/("""">

    </head>
    <body>
        """),format.raw/*21.32*/("""
        """),_display_(/*22.10*/content),format.raw/*22.17*/("""

      """),format.raw/*24.7*/("""<script src=""""),_display_(/*24.21*/routes/*24.27*/.Assets.versioned("javascripts/main.js")),format.raw/*24.67*/("""" type="text/javascript"></script>
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/main.scala.html
                  HASH: 414fee6752d4d3f29d72332e92eb818a940b7fdc
                  MATRIX: 987->260|1111->291|1138->292|1278->405|1293->411|1357->453|1396->517|1432->526|1487->554|1513->559|1602->621|1617->627|1680->668|1768->729|1783->735|1844->774|1907->899|1944->909|1972->916|2007->924|2048->938|2063->944|2124->984
                  LINES: 26->7|31->8|32->9|35->12|35->12|35->12|36->13|37->14|37->14|37->14|38->15|38->15|38->15|39->16|39->16|39->16|43->21|44->22|44->22|46->24|46->24|46->24|46->24
                  -- GENERATED --
              */
          