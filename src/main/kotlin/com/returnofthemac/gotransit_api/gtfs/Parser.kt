package com.returnofthemac.gotransit_api.gtfs

import com.univocity.parsers.common.processor.BeanListProcessor
import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import java.io.InputStreamReader
import java.io.Reader

open class Parser () {
    fun getReader (relativePath: String): Reader {
        return InputStreamReader(this.javaClass.getResourceAsStream(relativePath), "UTF-8")
    }

    inline fun <reified T: Any> parse (path: String) : List<T> {
        val parserSettings = CsvParserSettings()
        val rowProcessor = BeanListProcessor<T>(T::class.java)

        parserSettings.rowProcessor = rowProcessor
        parserSettings.isHeaderExtractionEnabled = true

        val parser = CsvParser(parserSettings)

        parser.parse(getReader(path))

        return rowProcessor.beans
    }
}
