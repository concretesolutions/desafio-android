package sergio.com.br.desafioandroid

import org.junit.Assert.assertEquals
import org.junit.Test
import sergio.com.br.desafioandroid.utils.Utils

class UtilsUnitTest {
    @Test
    fun date_formater_isCorrect() {
        var testDate = "2019-08-08T11:45:28Z"
        assertEquals("08/08/2019", Utils.formartDate(testDate))
    }

    @Test
    fun formated_number_isCorrect() {
        assertEquals("110kk", Utils.getFormatedNumber(110000000))
        assertEquals("11kk", Utils.getFormatedNumber(11000000))
        assertEquals("1kk", Utils.getFormatedNumber(1100000))
        assertEquals("100k", Utils.getFormatedNumber(100000))
        assertEquals("10k", Utils.getFormatedNumber(10000))
        assertEquals("1k", Utils.getFormatedNumber(1000))
        assertEquals("125", Utils.getFormatedNumber(125))
    }
}