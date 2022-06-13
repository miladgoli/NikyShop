package com.example.nikyshop.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.nikyshop.R
import com.example.nikyshop.databinding.ActivityMainBinding
import com.example.nikyshop.model.entity.User
import com.example.nikyshop.model.utils.Methods
import com.example.nikyshop.viewmodel.user.UserViewModel
import com.example.nikyshop.viewmodel.user.UserViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var user: User
    lateinit var userViewModel: UserViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initialize binding view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup toolbar app
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        //initialize viewmodel
        userViewModel = ViewModelProvider(
            this,
            UserViewModelProvider(this)
        ).get(UserViewModel::class.java)

        //get user is login from before page
        user = intent.extras?.getParcelable<User>("user")!!

        //check user = if admin=go to admin page  if user=go to user page
        if (user.isAdmin) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMain, AdminFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMain, UserFragment())
                .commit()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //initialize menu toolbar for exit account
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //on click exit button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {

                //show dialog and exit account
                Methods.showSignoutDialog(this) {
                    supportFragmentManager.beginTransaction()
                        .remove(UserFragment())
                        .commit()
                    val intent = Intent(this, EntranceActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }
                true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}