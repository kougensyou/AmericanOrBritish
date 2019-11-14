package com.example.americanorbritish

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var database: FirebaseDatabase? = null
    private var auth: FirebaseAuth? = null
    private var myRef: DatabaseReference? = null

    private var isAdmin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        myRef = database!!.reference
        myRef!!.child("admins").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child(auth!!.uid!!).exists() && dataSnapshot.child(auth!!.uid!!).value!!.toString() == "true")
                    isAdmin = true
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_test) {
            startActivity(Intent(this@MainActivity, Tests::class.java))
        } else if (id == R.id.nav_result) {
            if (isAdmin)
                startActivity(Intent(this@MainActivity, ResultsAdmin::class.java))
            else
                startActivity(Intent(this@MainActivity, Results::class.java))
        } else if (id == R.id.nav_respass) {
            startActivity(Intent(this@MainActivity, ResetPasswordActivity::class.java))
        } else if (id == R.id.nav_signout) {
            auth!!.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        } else if (id == R.id.nav_details) {
            startActivity(Intent(this@MainActivity, AddDetails::class.java))
        } else if (id == R.id.about_details) {
            startActivity(Intent(this@MainActivity, AboutUsActivity::class.java))
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}
