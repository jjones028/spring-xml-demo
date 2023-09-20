package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "NAXML-POSJournls")
data class POSJournal(
    @field:JacksonXmlProperty(localName = "TransmissionHeader")
    val transmissionHeader: TransmissionHeader,

    @field:JacksonXmlProperty(localName = "JournalReport")
    val journalReport: JournalReport
)
