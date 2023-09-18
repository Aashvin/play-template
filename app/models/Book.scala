package models

import play.api.libs.json.{Json, OFormat}

//case class IndustryIdentifiers (
//    _type: String,
//    identifier: String
//)
//
//case class ReadingModes (
//    text: Boolean,
//    image: Boolean
//)
//
//case class PanelizationSummary (
//    containsEpubBubbles: Boolean,
//    containsImageBubbles: Boolean
//)
//
//case class ImageLinks (
//    smallThumbnail: String,
//    thumbnail: String
//)
//
//case class VolumeInfo (
//    title: String,
//    authors: Array[String],
//    publishedDate: Int,
//    industryIdentifiers: List[IndustryIdentifiers],
//    readingModes: ReadingModes,
//    pageCount: Int,
//    printType: String,
//    maturityRating: String,
//    allowAnonLogging: Boolean,
//    contentVersion: String,
//    panelizationSummary: PanelizationSummary,
//    imageLinks: ImageLinks,
//    language: String,
//    previewLink: String,
//    infoLink: String,
//    canonicalVolumeLink: String
//)
//
//
//case class SaleInfo (
//    country: String,
//    saleability: String,
//    isEbook: Boolean,
//    buyLink: String
//)
//
//case class DownloadLinks (
//    isAvailable: Boolean,
//    downloadLink: String
//)
//
//case class AccessInfo (
//    country: String,
//    viewability: String,
//    embeddable: Boolean,
//    publicDomain: Boolean,
//    textToSpeechPermission: String,
//    epub: DownloadLinks,
//    pdf: DownloadLinks,
//    webReaderLink: String,
//    accessViewStatus: String,
//    quoteSharingAllowed: Boolean
//)
//
//case class SearchInfo (
//    textSnippet: String
//)
//
//case class Book (
//    kind: String,
//    _id: String,
//    etag: String,
//    selfLink: String,
//    volumeInfo: VolumeInfo,
//    saleInfo: SaleInfo,
//    accessInfo: AccessInfo,
//    searchInfo: SearchInfo
//)
//
//object Book {
//    implicit val formats: OFormat[Book] = Json.format[Book]
//}

case class Book (
    _id: String,
    name: String,
    description: String,
    numSales: Int,
)

object Book {
    implicit val formats: OFormat[Book] = Json.format[Book]
}