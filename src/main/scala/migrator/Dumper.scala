package migrator

import java.io.{File, PrintWriter}

import org.apache.commons.io.IOUtils


class Dumper(folderPath: String) {


  def writeMarkdownFile(markdownEntry: BloggerMarkdownEntry): Unit ={
    println(s"Writing ${markdownEntry.fileName}")
    val filePath = s"$folderPath${markdownEntry.fileName}.md"
    val outFile = new File(filePath)
    val writer = new PrintWriter(outFile)
    try {
      IOUtils.write(markdownEntry.markdownText, writer)
    } finally {
      writer.close()
    }
  }

}

object Dumper {

  def apply(folderPath: String): Dumper = {
    val folder = new File(folderPath)
    if(!folder.exists)  folder.mkdir()
    new Dumper(folderPath)
  }

}
