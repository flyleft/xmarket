package me.jcala.xmarket.mvp.team.add;

import android.widget.EditText;

public interface TeamAddPresenter {

   void submit(EditText teamTitle,EditText teamDesc,String teamImg,String idImg);
}
