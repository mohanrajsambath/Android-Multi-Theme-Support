package mohan.exp.javamultitheme.refer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.overflow_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == R.id.settings)
      startActivity(Intent(this, SettingsActivity::class.java))

    return super.onOptionsItemSelected(item)
  }

}
