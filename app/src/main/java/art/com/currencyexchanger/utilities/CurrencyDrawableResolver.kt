package art.com.currencyexchanger.utilities

import art.com.currencyexchanger.R
import art.com.currencyexchanger.models.domain.CurrencyType

class CurrencyDrawableResolver{
    companion object {
        const val EMPTY: Int = -1

        fun resolve(currencyType: String): Int {
            return when (currencyType) {
                CurrencyType.ADA.type -> R.drawable.ada
                CurrencyType.ADX.type -> R.drawable.adx
                CurrencyType.AE.type -> R.drawable.ae
                CurrencyType.AOA.type -> R.drawable.aoa
                CurrencyType.ARDR.type -> R.drawable.ardr
                CurrencyType.ARK.type -> R.drawable.ark
                CurrencyType.BAT.type -> R.drawable.bat
                CurrencyType.BCC.type -> R.drawable.bcc
                CurrencyType.BCD.type -> R.drawable.bcd
                CurrencyType.BCH.type -> R.drawable.bch
                CurrencyType.BCN.type -> R.drawable.bcn
                CurrencyType.BLOCK.type -> R.drawable.block
                CurrencyType.BNB.type -> R.drawable.bnb
                CurrencyType.BNT.type -> R.drawable.bnt
                CurrencyType.BTC.type -> R.drawable.btc
                CurrencyType.BTCD.type -> R.drawable.btcd
                CurrencyType.BTG.type -> R.drawable.btg
                CurrencyType.BTHABC.type -> R.drawable.bthabc
                CurrencyType.BTM.type -> R.drawable.btm
                CurrencyType.BTS.type -> R.drawable.bts
                CurrencyType.CNX.type -> R.drawable.cnx
                CurrencyType.CVC.type -> R.drawable.cvc
                CurrencyType.DASH.type -> R.drawable.dash
                CurrencyType.DBG.type -> R.drawable.dbg
                CurrencyType.DCR.type -> R.drawable.dcr
                CurrencyType.DOGE.type -> R.drawable.doge
                CurrencyType.EDG.type -> R.drawable.edg
                CurrencyType.EMC.type -> R.drawable.emc
                CurrencyType.EOS.type -> R.drawable.eos
                CurrencyType.ETC.type -> R.drawable.etc
                CurrencyType.ETH.type -> R.drawable.eth
                CurrencyType.ETHOS.type -> R.drawable.ethos
                CurrencyType.ETP.type -> R.drawable.etp
                CurrencyType.FCT.type -> R.drawable.fct
                CurrencyType.FUN.type -> R.drawable.fun_
                CurrencyType.GAME.type -> R.drawable.game
                CurrencyType.GAS.type -> R.drawable.gas
                CurrencyType.GBYTE.type -> R.drawable.gbyte
                CurrencyType.GNO.type -> R.drawable.gno
                CurrencyType.GNT.type -> R.drawable.gnt
                CurrencyType.GRS.type -> R.drawable.grs
                CurrencyType.HSR.type -> R.drawable.hsr
                CurrencyType.ICN.type -> R.drawable.icn
                CurrencyType.ICX.type -> R.drawable.icx
                CurrencyType.IOT.type -> R.drawable.iot
                CurrencyType.KMD.type -> R.drawable.kmd
                CurrencyType.KNC.type -> R.drawable.knc
                CurrencyType.LINK.type -> R.drawable.link
                CurrencyType.LSK.type -> R.drawable.lsk
                CurrencyType.LTC.type -> R.drawable.ltc
                CurrencyType.MAID.type -> R.drawable.maid
                CurrencyType.MCO.type -> R.drawable.mco
                CurrencyType.MIOTA.type -> R.drawable.miota
                CurrencyType.MKR.type -> R.drawable.mkr
                CurrencyType.MNX.type -> R.drawable.mnx
                CurrencyType.MONA.type -> R.drawable.mona
                CurrencyType.MTL.type -> R.drawable.mtl
                CurrencyType.NANO.type -> R.drawable.nano
                CurrencyType.NAV.type -> R.drawable.nav
                CurrencyType.NEO.type -> R.drawable.neo
                CurrencyType.NPXS.type -> R.drawable.npxs
                CurrencyType.NXS.type -> R.drawable.nxs
                CurrencyType.NXT.type -> R.drawable.nxt
                CurrencyType.OMG.type -> R.drawable.omg
                CurrencyType.ONT.type -> R.drawable.ont
                CurrencyType.PAY.type -> R.drawable.pay
                CurrencyType.PIVX.type -> R.drawable.pivx
                CurrencyType.POT.type -> R.drawable.pot
                CurrencyType.POWER.type -> R.drawable.power
                CurrencyType.PPC.type -> R.drawable.ppc
                CurrencyType.PPT.type -> R.drawable.ppt
                CurrencyType.PURA.type -> R.drawable.pura
                CurrencyType.QASH.type -> R.drawable.qash
                CurrencyType.QTUM.type -> R.drawable.qtum
                CurrencyType.RDN.type -> R.drawable.rdn
                CurrencyType.REP.type -> R.drawable.rep
                CurrencyType.RIPPLE.type -> R.drawable.ripple
                CurrencyType.SALT.type -> R.drawable.salt
                CurrencyType.SAN.type -> R.drawable.san
                CurrencyType.SC.type -> R.drawable.sc
                CurrencyType.SKY.type -> R.drawable.sky
                CurrencyType.SNGL.type -> R.drawable.sngl
                CurrencyType.SNT.type -> R.drawable.snt
                CurrencyType.START.type -> R.drawable.start
                CurrencyType.STEEM.type -> R.drawable.steem
                CurrencyType.STORJ.type -> R.drawable.storj
                CurrencyType.SYS.type -> R.drawable.sys
                CurrencyType.TRX.type -> R.drawable.trx
                CurrencyType.UBQ.type -> R.drawable.ubq
                CurrencyType.UDST.type -> R.drawable.udst
                CurrencyType.USDT.type -> R.drawable.usdt
                CurrencyType.VEN.type -> R.drawable.ven
                CurrencyType.VET.type -> R.drawable.vet
                CurrencyType.VTC.type -> R.drawable.vtc
                CurrencyType.WAVES.type -> R.drawable.waves
                CurrencyType.WTC.type -> R.drawable.wtc
                CurrencyType.XEM.type -> R.drawable.xem
                CurrencyType.XLM.type -> R.drawable.xlm
                CurrencyType.XMR.type -> R.drawable.xmr
                CurrencyType.XRP.type -> R.drawable.xrp
                CurrencyType.XTZ.type -> R.drawable.xtz
                CurrencyType.XUC.type -> R.drawable.xuc
                CurrencyType.XVG.type -> R.drawable.xvg
                CurrencyType.XZC.type -> R.drawable.xzc
                CurrencyType.ZEC.type -> R.drawable.zec
                CurrencyType.ZEN.type -> R.drawable.zen
                CurrencyType.ZIL.type -> R.drawable.zil
                CurrencyType.ZRX.type -> R.drawable.zrx
                else -> EMPTY
            }
        }
    }
}
