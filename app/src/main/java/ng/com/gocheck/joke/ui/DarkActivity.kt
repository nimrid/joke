package ng.com.gocheck.joke.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_dark.*
import kotlinx.android.synthetic.main.activity_dark.delivery
import kotlinx.android.synthetic.main.activity_dark.joke
import kotlinx.android.synthetic.main.activity_dark.joke_type
import kotlinx.android.synthetic.main.activity_dark.setup
import ng.com.gocheck.joke.R
import ng.com.gocheck.joke.networkCall.ApiRequest
import ng.com.gocheck.joke.networkCall.JokeCategoryRepo
import ng.com.gocheck.joke.viewmodel.JokeCatFactory
import ng.com.gocheck.joke.viewmodel.JokeCategoryVM

class DarkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark)

        val api = ApiRequest()
        val repository = JokeCategoryRepo(api)
        val factory = JokeCatFactory(repository)
        val viewModel = ViewModelProvider(this, factory)
            .get(JokeCategoryVM::class.java)

        if (NetworkAvailable.init(this)) {

            viewModel.getDarkJoke()

            viewModel.joke.observe(this, Observer {
                joke_type.text = "Joke type: ${it.type}"
                joke.text = it.joke
                setup.text = "Setup: ${it.setup}"
                delivery.text = "Delivery: ${it.delivery}"

                new_joke.setOnClickListener {
                    viewModel.getDarkJoke()
                }
            })
        }else {
            Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show()
        }

    }


}