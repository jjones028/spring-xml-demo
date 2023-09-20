package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty


data class JournalReport(
    @field:JacksonXmlProperty(localName = "OtherEvent")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val otherEvents: MutableList<OtherEvent> = mutableListOf(),

    @field:JacksonXmlProperty(localName = "SaleEvent")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val saleEvents: MutableList<SaleEvent> = mutableListOf(),

    @field:JacksonXmlProperty(localName = "FinancialEvent")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val financialEvents: MutableList<FinancialEvent> = mutableListOf()
) {
    fun setOtherEvents(value: List<OtherEvent>) {
        otherEvents.addAll(value)
    }

    fun setSaleEvents(value: List<SaleEvent>) {
        saleEvents.addAll(value)
    }

    fun setFinancialEvents(value: List<FinancialEvent>) {
        financialEvents.addAll(value)
    }
}
