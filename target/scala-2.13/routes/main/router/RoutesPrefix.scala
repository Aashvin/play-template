// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/aashvin.relwani/Documents/GitHub/play-template/conf/routes
// @DATE:Wed Sep 13 10:08:56 BST 2023


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
