package migrator


object Main extends App {

  private [this] val bloggerXMLFilePath = "file:///D://_web//downs//data//blogger.xml"
  private [this] val outFolder = "D:\\_web\\downs\\data\\out2\\"

  def process()  {
    val bloggerXML = new BloggerXML(bloggerXMLFilePath)
    val bloggerEntries = bloggerXML.parse()
    val dumper = Dumper(outFolder)
    bloggerEntries.foreach { entry =>
      val markdownEntry = BloggerMarkdown.convert(entry)
      dumper.writeMarkdownFile(markdownEntry)
    }

  }

  process()

}
