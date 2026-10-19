// ARCHIVO DE PRUEBA: código inseguro a propósito para validar Semgrep. No usar en producción.
package com.prueba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.X509TrustManager;

public class Inseguro {

    // Cifrado débil: DES en modo ECB
    public byte[] cifrar(byte[] datos, byte[] llave) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(llave, "DES"));
        return cipher.doFinal(datos);
    }

    // Hash débil: MD5
    public byte[] hash(String texto) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(texto.getBytes());
    }

    // Inyección SQL: consulta armada concatenando la entrada del usuario
    public Cursor buscar(SQLiteDatabase db, String nombre) {
        return db.rawQuery("SELECT * FROM clientes WHERE nombre = '" + nombre + "'", null);
    }

    // TrustManager que acepta cualquier certificado (rompe TLS)
    public static class ConfiaEnTodo implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) { }
        public void checkServerTrusted(X509Certificate[] chain, String authType) { }
        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
    }
}
