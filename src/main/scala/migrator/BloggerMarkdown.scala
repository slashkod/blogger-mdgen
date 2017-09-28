package migrator

import com.overzealous.remark.{Options, Remark}

case class BloggerMarkdownEntry(fileName: String, markdownText: String )

object BloggerMarkdown {

  private [this] val remark = new Remark(bloggerMarkdown)

  def convert(bloggerEntry: BloggerEntry): BloggerMarkdownEntry = {

    val markdown = remark.convertFragment(bloggerEntry.contentText)
    val mdText =
      s"""
         |${buildMetadata(bloggerEntry)}
         |
         |$markdown
         |
      """.stripMargin
    val fileName = bloggerEntry.title.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase.replaceAll(" ", "_")
    BloggerMarkdownEntry(fileName, mdText)

  }

  private [this] def bloggerMarkdown = {
    val remarkOpts = Options.markdown
    //remarkOpts.simpleLinkIds = true
    remarkOpts.fencedCodeBlocks = Options.FencedCodeBlocks.ENABLED_BACKTICK
    remarkOpts.inlineLinks = true
    remarkOpts.hardwraps = true
    remarkOpts
  }

  private [this] def buildMetadata(bloggerEntry: BloggerEntry): String = {
    s"""
         |---
         |
         |title: "${bloggerEntry.title}"
         |linktitle: "${bloggerEntry.title}"
         |author: "slashkod"
         |date: "${bloggerEntry.published}"
         |
         |---
         |
    """.stripMargin

  }

}
