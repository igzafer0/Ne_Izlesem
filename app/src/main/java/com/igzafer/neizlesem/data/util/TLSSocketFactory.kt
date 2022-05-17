package com.igzafer.neizlesem.data.util

import java.net.InetAddress
import java.net.Socket
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

class TLSSocketFactory:SSLSocketFactory(){
    private val delegate: SSLSocketFactory? = null
   // private val trustManagers: Array<TrustManager>()
    override fun createSocket(p0: Socket?, p1: String?, p2: Int, p3: Boolean): Socket {
       // return enableTLSOnSocket(delegate.createSocket(p0, p1, p2, p3));
       TODO("Not yet implemented")

   }

    override fun createSocket(p0: String?, p1: Int): Socket {
        TODO("Not yet implemented")
    }

    override fun createSocket(p0: String?, p1: Int, p2: InetAddress?, p3: Int): Socket {
        TODO("Not yet implemented")
    }

    override fun createSocket(p0: InetAddress?, p1: Int): Socket {
        TODO("Not yet implemented")
    }

    override fun createSocket(p0: InetAddress?, p1: Int, p2: InetAddress?, p3: Int): Socket {
        TODO("Not yet implemented")
    }

    override fun getDefaultCipherSuites(): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getSupportedCipherSuites(): Array<String> {
        TODO("Not yet implemented")
    }
    private fun enableTLSOnSocket(socket: Socket?): Socket? {
        if (socket != null && socket is SSLSocket) {
            (socket).enabledProtocols = arrayOf("TLSv1.1", "TLSv1.2")
        }
        return socket
    }
}