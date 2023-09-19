package models

import play.api.libs.json.{JsNumber, Json, OFormat}

case class IndustryIdentifiers (
    `type`: String,
    identifier: String
)

object IndustryIdentifiers {
    implicit val formats: OFormat[IndustryIdentifiers] = Json.format[IndustryIdentifiers]
}

case class ReadingModes (
    text: Boolean,
    image: Boolean
)

object ReadingModes {
    implicit val formats: OFormat[ReadingModes] = Json.format[ReadingModes]
}

case class PanelizationSummary (
    containsEpubBubbles: Boolean,
    containsImageBubbles: Boolean
)

object PanelizationSummary {
    implicit val formats: OFormat[PanelizationSummary] = Json.format[PanelizationSummary]
}

case class ImageLinks (
    smallThumbnail: String,
    thumbnail: String
)

object ImageLinks {
    implicit val formats: OFormat[ImageLinks] = Json.format[ImageLinks]
}

case class VolumeInfo (
    title: String,
    authors: Option[Array[String]],
    publishedDate: String,
    industryIdentifiers: Array[IndustryIdentifiers],
    readingModes: ReadingModes,
    pageCount: Int,
    printType: String,
    maturityRating: String,
    allowAnonLogging: Boolean,
    contentVersion: String,
    panelizationSummary: PanelizationSummary,
    imageLinks: Option[ImageLinks],
    language: String,
    previewLink: String,
    infoLink: String,
    canonicalVolumeLink: String
)

object VolumeInfo {
    implicit val formats: OFormat[VolumeInfo] = Json.format[VolumeInfo]
}


case class SaleInfo (
    country: String,
    saleability: String,
    isEbook: Boolean,
    buyLink: Option[String]
)

object SaleInfo {
    implicit val formats: OFormat[SaleInfo] = Json.format[SaleInfo]
}

case class DownloadLinks (
    isAvailable: Boolean,
    downloadLink: Option[String],
    acsTokenLink: Option[String]
)

object DownloadLinks {
    implicit val formats: OFormat[DownloadLinks] = Json.format[DownloadLinks]
}

case class AccessInfo (
    country: String,
    viewability: String,
    embeddable: Boolean,
    publicDomain: Boolean,
    textToSpeechPermission: String,
    epub: DownloadLinks,
    pdf: DownloadLinks,
    webReaderLink: String,
    accessViewStatus: String,
    quoteSharingAllowed: Boolean
)

object AccessInfo {
    implicit val formats: OFormat[AccessInfo] = Json.format[AccessInfo]
}

case class SearchInfo (
    textSnippet: String
)

object SearchInfo {
    implicit val formats: OFormat[SearchInfo] = Json.format[SearchInfo]
}

case class GoogleBook (
    kind: String,
    id: String,
    etag: String,
    selfLink: String,
    volumeInfo: VolumeInfo,
    saleInfo: SaleInfo,
    accessInfo: AccessInfo,
    searchInfo: SearchInfo
)

object GoogleBook {
    implicit val formats: OFormat[GoogleBook] = Json.format[GoogleBook]
}