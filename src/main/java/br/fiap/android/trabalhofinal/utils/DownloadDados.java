package br.fiap.android.trabalhofinal.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by usuario on 15/04/2017.
 */

public class DownloadDados extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String linha;
            StringBuffer buffer = new StringBuffer();
            while((linha = reader.readLine()) != null) {
                buffer.append(linha);
                buffer.append("\n");
            }
            Log.d("teste",  buffer.toString());
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute () {
// Este passo é usado para configurar a tarefa, por exemplo, mostrando uma barra de progresso na interface do usuário.
    }
//    @Override
//    protected void onProgressUpdate (Progress... values) {
//// Este método é usado para exibir qualquer forma de progresso na interface do usuário, enquanto a tarefa ainda está em execução.
//    }
//    @Override
//    protected void onPostExecute (Result result) {
//// O resultado da execução em background é passado para este passo como um parâmetro.
//    }
}