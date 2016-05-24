package com.returnofthemac.gotransit_api.gtfs

import com.univocity.parsers.common.processor.BeanListProcessor
import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import java.io.InputStreamReader
import java.io.Reader

class StopsParser (val stopsFile: String) {
    fun getReader (relativePath: String): Reader {
        return InputStreamReader(this.javaClass.getResourceAsStream(relativePath), "UTF-8")
    }

    fun parse (): List<Stop> {
        val parserSettings = CsvParserSettings()
        val rowProcessor = BeanListProcessor<Stop>(Stop::class.java)

        parserSettings.rowProcessor = rowProcessor
        parserSettings.isHeaderExtractionEnabled = true

        val parser = CsvParser(parserSettings)

        parser.parse(getReader(stopsFile))

        return rowProcessor.getBeans()
    }
}
