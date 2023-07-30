package be.melyuki.demo03_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.MessageFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnMsg1 : Button
    lateinit var btnMsg2 : Button
    lateinit var btnMsg3 : Button
    lateinit var tvMsg : TextView

    lateinit var inputFormData : EditText
    lateinit var btnFormValid : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récup des éléments -> Binding entre le code et la vue
        btnMsg1 = findViewById(R.id.btn_main_message_popup_1)
        btnMsg2 = findViewById(R.id.btn_main_message_popup_2)
        btnMsg3 = findViewById(R.id.btn_main_message_popup_3)
        tvMsg = findViewById(R.id.tv_main_message_popup)

        inputFormData = findViewById(R.id.et_main_form_data)
        btnFormValid = findViewById(R.id.btn_main_form_valid)

        // Cas 1 : Ecouter l'event "Click" sur les boutons "popup"
        //       : Gestion de l'event "Click" via l'activité qui implemente "View.OnClickListener"
        btnMsg1.setOnClickListener(this)
        btnMsg2.setOnClickListener(this)
        btnMsg3.setOnClickListener(this)

        // Cas 2 : Gestion de l'event "Click" via une lambda
        btnFormValid.setOnClickListener { processForm() }
    }

    private fun processForm() {
        // Récup de la valeur de l'EditText
        val formData : String = inputFormData.text.toString()

        // On ne fait pas comme ça !! -> Utiliser les ressources
//        val toastMsg1 : String = MessageFormat.format("Numéro: {0}", formData)
//        val toastMsg2 : String = "Numéro: %s".format(formData)

        // Avec les ressources
        val toastMsg1 : String = MessageFormat.format(getString(R.string.msg_main_toast_form), formData)
        val toastMsg2 : String = getString(R.string.msg_main_toast_form2).format(formData)

//        val toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
//        // Gestion de la position: No-op depuis Android R (Fonctionne sur les anciennes versions)
//        toast.setGravity(Gravity.CENTER, 0, 0)
//        toast.show()
        Toast.makeText(this, toastMsg1, Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_main_message_popup_1 -> showMessage("Salut!")
            R.id.btn_main_message_popup_2 -> showMessage("GoodBye!")
            R.id.btn_main_message_popup_3 -> showMessage("John Wick!")
            else -> throw RuntimeException("Not Implemented!")}
    }

    private fun showMessage(msg: String) {

        tvMsg.setText(msg)
    }
}