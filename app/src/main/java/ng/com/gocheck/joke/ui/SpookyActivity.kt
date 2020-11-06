package ng.com.gocheck.joke.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_spooky.delivery
import kotlinx.android.synthetic.main.activity_spooky.joke
import kotlinx.android.synthetic.main.activity_spooky.joke_type
import kotlinx.android.synthetic.main.activity_spooky.new_joke
import kotlinx.android.synthetic.main.activity_spooky.setup
import ng.com.gocheck.joke.R
import ng.com.gocheck.joke.networkCall.ApiRequest
import ng.com.gocheck.joke.networkCall.JokeCategoryRepo
import ng.com.gocheck.joke.viewmodel.JokeCatFactory
import ng.com.gocheck.joke.viewmodel.JokeCategoryVM

class SpookyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spooky)

        val api = ApiRequest()
        val repository = JokeCategoryRepo(api)
        val factory = JokeCatFactory(repository)
        val viewModel = ViewModelProvider(this, factory)
            .get(JokeCategoryVM::class.java)

        if (NetworkAvailable.init(this)) {

            viewModel.getSpookyJoke()

            viewModel.joke.observe(this, Observer {
                joke_type.text = "Joke type: ${it.type}"
                joke.text = it.joke
                setup.text = "Setup: ${it.setup}"
                delivery.text = "Delivery: ${it.delivery}"

                new_joke.setOnClickListener {
                    viewModel.getSpookyJoke()
                }
            })
        }else {
            Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show()
        }
    }

}