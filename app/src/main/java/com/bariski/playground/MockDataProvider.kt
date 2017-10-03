package com.bariski.playground

import android.content.ContentValues.TAG
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MockDataProvider {

    companion object {
        private val mock_data = "[{\"id\":1,\"firstName\":\"Ricard\",\"email\":\"raiskovitch0@reuters.com\"},{\"id\":2,\"firstName\":\"Agatha\",\"email\":\"adonaldson1@spotify.com\"},{\"id\":3,\"firstName\":\"Federica\",\"email\":\"fbeckworth2@businesswire.com\"},{\"id\":4,\"firstName\":\"Cyndia\",\"email\":\"ccranmere3@cbc.ca\"},{\"id\":5,\"firstName\":\"Averyl\",\"email\":\"agreenall4@dropbox.com\"},{\"id\":6,\"firstName\":\"Valerye\",\"email\":\"vhighwood5@artisteer.com\"},{\"id\":7,\"firstName\":\"Aymer\",\"email\":\"aastle6@opensource.org\"},{\"id\":8,\"firstName\":\"Myrtle\",\"email\":\"msimmank7@exblog.jp\"},{\"id\":9,\"firstName\":\"Thomasine\",\"email\":\"twoakes8@mail.ru\"},{\"id\":10,\"firstName\":\"Stormy\",\"email\":\"svongladbach9@arizona.edu\"},{\"id\":11,\"firstName\":\"Beck\",\"email\":\"btetlowa@macromedia.com\"},{\"id\":12,\"firstName\":\"Philis\",\"email\":\"phucksterb@accuweather.com\"},{\"id\":13,\"firstName\":\"Andee\",\"email\":\"aburnyatec@goo.gl\"},{\"id\":14,\"firstName\":\"Grier\",\"email\":\"gclemensd@live.com\"},{\"id\":15,\"firstName\":\"Chris\",\"email\":\"ctamblyne@hugedomains.com\"},{\"id\":16,\"firstName\":\"Bax\",\"email\":\"bralestonef@skyrock.com\"},{\"id\":17,\"firstName\":\"Nicola\",\"email\":\"ndumberellg@accuweather.com\"},{\"id\":18,\"firstName\":\"Pammi\",\"email\":\"pbeafordh@cargocollective.com\"},{\"id\":19,\"firstName\":\"Teresa\",\"email\":\"tbischofi@senate.gov\"},{\"id\":20,\"firstName\":\"Alyosha\",\"email\":\"akoppenj@blog.com\"},{\"id\":21,\"firstName\":\"Isahella\",\"email\":\"iattridgek@java.com\"},{\"id\":22,\"firstName\":\"Hedvig\",\"email\":\"hmatejovskyl@wisc.edu\"},{\"id\":23,\"firstName\":\"Gabriella\",\"email\":\"gwaldrenm@upenn.edu\"},{\"id\":24,\"firstName\":\"Danyelle\",\"email\":\"dmckeonn@printfriendly.com\"},{\"id\":25,\"firstName\":\"Sapphire\",\"email\":\"sjahnigo@posterous.com\"},{\"id\":26,\"firstName\":\"Gracia\",\"email\":\"gjendrassikp@ihg.com\"},{\"id\":27,\"firstName\":\"Jasen\",\"email\":\"jcarberryq@indiegogo.com\"},{\"id\":28,\"firstName\":\"Jillayne\",\"email\":\"jadhamsr@admin.ch\"},{\"id\":29,\"firstName\":\"Darb\",\"email\":\"dstaileys@cdc.gov\"},{\"id\":30,\"firstName\":\"Rina\",\"email\":\"rcheeset@epa.gov\"},{\"id\":31,\"firstName\":\"Ericka\",\"email\":\"egoselingu@histats.com\"},{\"id\":32,\"firstName\":\"Aloisia\",\"email\":\"amcgralev@webeden.co.uk\"},{\"id\":33,\"firstName\":\"Sayre\",\"email\":\"skirkamw@reuters.com\"},{\"id\":34,\"firstName\":\"Ignace\",\"email\":\"isalazarx@istockphoto.com\"},{\"id\":35,\"firstName\":\"Emogene\",\"email\":\"ebuddingtony@who.int\"},{\"id\":36,\"firstName\":\"Brietta\",\"email\":\"bdurnanz@google.ru\"},{\"id\":37,\"firstName\":\"Nikaniki\",\"email\":\"nfardo10@jigsy.com\"},{\"id\":38,\"firstName\":\"Judy\",\"email\":\"jkernell11@shinystat.com\"},{\"id\":39,\"firstName\":\"Maximo\",\"email\":\"mcarreyette12@ow.ly\"},{\"id\":40,\"firstName\":\"Rozina\",\"email\":\"rlowne13@reddit.com\"},{\"id\":41,\"firstName\":\"Verna\",\"email\":\"vwhenman14@creativecommons.org\"},{\"id\":42,\"firstName\":\"Fabe\",\"email\":\"flate15@studiopress.com\"},{\"id\":43,\"firstName\":\"Maxwell\",\"email\":\"mmullett16@istockphoto.com\"},{\"id\":44,\"firstName\":\"Noellyn\",\"email\":\"nponter17@istockphoto.com\"},{\"id\":45,\"firstName\":\"Bennett\",\"email\":\"bmaddigan18@hud.gov\"},{\"id\":46,\"firstName\":\"Ardelia\",\"email\":\"atremayne19@ovh.net\"},{\"id\":47,\"firstName\":\"Roman\",\"email\":\"rdudding1a@miibeian.gov.cn\"},{\"id\":48,\"firstName\":\"Obie\",\"email\":\"oleedes1b@imageshack.us\"},{\"id\":49,\"firstName\":\"Berni\",\"email\":\"bkarpmann1c@china.com.cn\"},{\"id\":50,\"firstName\":\"Kara\",\"email\":\"kbasler1d@bloglovin.com\"}]"
        private val DATA: List<User> = Gson().fromJson(mock_data, object : TypeToken<List<User>>() {}.type)


        fun loadBefore(currentBeginKey: Int, pageSize: Int): MutableList<User> {
            Log.d(TAG, "loadBefore: thread=${Thread.currentThread()}, beginKey=$currentBeginKey, pageSize=$pageSize")
            Log.d(TAG, "isMainThread=${Looper.myLooper() == Looper.getMainLooper()}")
            val list = ArrayList<User>()
            var cur = currentBeginKey
            return if (cur > 1) {
                var upto = cur - pageSize
                if (upto < 1) {
                    upto = 1
                }
                (cur - 1 downTo upto).mapTo(list) { DATA[it] }
                list
            } else {
                list
            }
        }


        fun loadAfter(currentEndKey: Int, pageSize: Int): MutableList<User> {
            Log.d(TAG, "thread=${Thread.currentThread()}, loadAfter: currentEndKey=$currentEndKey, pagesize=$pageSize")
            Log.d(TAG, "isMainThread=${Looper.myLooper() == Looper.getMainLooper()}")
            var list = ArrayList<User>()
            if (currentEndKey >= DATA.size) {
                return list
            }
            var upto = currentEndKey + pageSize
            if (upto >= DATA.size) {
                upto = DATA.size - 1
            }
            (currentEndKey..upto).mapTo(list) { DATA[it] }
            return list
        }
    }


}
