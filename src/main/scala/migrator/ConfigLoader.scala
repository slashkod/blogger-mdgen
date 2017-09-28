package migrator

import com.typesafe.config.{Config, ConfigFactory}


class ConfigLoader(fileNameOption: Option[String] = None) {
  val config: Config = fileNameOption.fold(ifEmpty = ConfigFactory.load()) (file => ConfigFactory.load(file))
}