package ng.com.gocheck.joke.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ng.com.gocheck.joke.R
import ng.com.gocheck.joke.networkCall.NetworkConnection
import ng.com.gocheck.joke.viewmodel.JokeViewModel
import ng.com.gocheck.joke.viewmodel.JokeViewModelFactory
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    override val di by di()
    private val factory : JokeViewModelFactory by instance()
    private val networkConnection : NetworkConnection by instance()
    private lateinit var viewModel: JokeViewModel

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xmas_jokes.setOnClickListener {
            startActivity(Intent(this, XmasActivity::class.java))
        }

        dark_jokes.setOnClickListener {
            startActivity(Intent(this, DarkActivity::class.java))
        }

        miscellaneous_jokes.setOnClickListener {
            startActivity(Intent(this, MisscellActivity::class.java))
        }

        pun_jokes.setOnClickListener {
            startActivity(Intent(this, PunActivity::class.java))
        }

        programming_jokes.setOnClickListener {
            startActivity(Intent(this, ProgramActivity::class.java))
        }

        spooky_jokes.setOnClickListener {
            startActivity(Intent(this, SpookyActivity::class.java))
        }

        viewModel = ViewModelProviders.of(this, factory).get(JokeViewModel::class.java)

        val networkAvailable = NetworkAvailable.init(this)

        if (networkAvailable){
            Toast.makeText(this,"Connected", Toast.LENGTH_LONG).show()
            viewModel.getJoke()
            viewModel.joke.observe(this, Observer {
                joke_text.text = it.joke
                category_text.text = "Category: ${it.category}"
                type_text.text = "Type: ${it.type}"
                setup_text.text = it.setup
                delivery_text.text = it.delivery
            })

            fetch_joke.setOnClickListener {
                viewModel.getJoke()
            }
        }else {
            viewModel.retrieveDb().observe(this, Observer {joke ->
                if (joke != null){
                    joke_text.text = joke.joke
                    category_text.text = "Category: ${joke.category}"
                    type_text.text = "Type: ${joke.type}"
                    setup_text.text = joke.setup
                    delivery_text.text = joke.delivery
                }
            })
            Toast.makeText(this,"No internet connection", Toast.LENGTH_LONG).show()
        }


//        networkConnection.observe(this, Observer {isConnected ->
//            if (!isConnected){
////                layout_disconnected.visibility = View.VISIBLE
////                layout_connected.visibility = View.GONE
//
//            }else{
////                layout_connected.visibility = View.VISIBLE
////                layout_disconnected.visibility = View.GONE
//
//
//            }
//
//        })

    }

}
