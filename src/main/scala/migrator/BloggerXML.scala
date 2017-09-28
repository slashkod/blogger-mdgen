package migrator

import scala.xml.{Node, XML}

object BloggerEntry {
  object Constants {
    object BLOGGER_XML {
      val ENTRY = "entry"
      val PUBLISHED = "published"
      val CATEGORY = "category"
      val TERM = "@term"
      val TITLE = "title"
      val CONTENT = "content"
    }
    val UTF8_BOM = "\uFEFF"
    val HTTP = "http://"
  }
}

case class BloggerEntry(title: String, published: String, tags: Seq[String], contentText: String)

class BloggerXML(bloggerXMLFilePath: String) {

  import BloggerEntry.Constants.BLOGGER_XML._

  def parse(): Seq[BloggerEntry]  = {

    val bloggerXML = XML.load(bloggerXMLFilePath)
    val entries = bloggerXML \\ ENTRY
    entries.map(parseEntry)

  }

  private[this] def parseEntry(entry: Node): BloggerEntry = {

    val published = (entry \ PUBLISHED).text
    val categories = entry \\ CATEGORY
    val terms = categories.map(cat => (cat \ TERM).text)
    val tags = terms.filterNot(_.startsWith(BloggerEntry.Constants.HTTP))
    val title = (entry \ TITLE).text
    // removing some invalid characters
    val content = (entry \ CONTENT).text
                                                .replaceAll("[^\\x20-\\x7e\\x0A]", "")
                                                .trim()
                                                .replaceFirst("^([\\W]+)<", "<")

   // incinerate byte order markers here
    val contentText = Option(content).fold("") { s => if (s.startsWith(BloggerEntry.Constants.UTF8_BOM)) s.substring(1) else s }
    BloggerEntry(title, published, tags, contentText)
  }

}

