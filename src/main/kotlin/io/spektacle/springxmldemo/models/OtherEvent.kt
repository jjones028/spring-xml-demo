package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.time.LocalDate
import java.time.LocalTime

data class OtherEvent(
    @field:JacksonXmlProperty(localName = "TransactionID")
    val transactionId: Long,

    @field:JacksonXmlProperty(localName = "RegisterDetail")
    val registerDetail: RegisterDetail?,

    @field:JacksonXmlProperty(localName = "EventStartDate")
    val eventStartDate: LocalDate,

    @field:JacksonXmlProperty(localName = "eventStartTime")
    val eventStartTime: LocalTime,

    @field:JacksonXmlProperty(localName = "RegisterID")
    val registerId: Long,

    @field:JacksonXmlProperty(localName = "OtherEventExtension")
    val otherEventExtension: OtherEventExtension,

    @field:JacksonXmlProperty(localName = "EventEndDate")
    val eventEndDate: LocalDate,

    @field:JacksonXmlProperty(localName = "EventEndTime")
    val eventEndTime: LocalTime
)
