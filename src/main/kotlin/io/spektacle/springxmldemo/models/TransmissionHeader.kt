package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class TransmissionHeader(
    @field:JacksonXmlProperty(localName = "StoreLocationID")
    val storeNumber: String,

    @field:JacksonXmlProperty(localName = "VendorName")
    val vendorName: String,

    @field:JacksonXmlProperty(localName = "VendorModelVersion")
    val vendorModelVersion: String
)
