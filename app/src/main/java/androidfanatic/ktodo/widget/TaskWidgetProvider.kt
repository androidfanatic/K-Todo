package androidfanatic.ktodo.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v7.preference.PreferenceManager
import android.widget.RemoteViews
import androidfanatic.ktodo.R
import androidfanatic.ktodo.main.MainActivity
import androidfanatic.ktodo.main.MainPresenter


fun updateWidgets(context: Context) {
    val appWidgetManager = AppWidgetManager.getInstance(context)
    val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context, TaskWidgetProvider::class.java))
    if (appWidgetIds.size > 0) {
        TaskWidgetProvider().onUpdate(context, appWidgetManager, appWidgetIds)
    }
}

class TaskWidgetProvider : AppWidgetProvider() {


    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {

        val prefMgr = PreferenceManager.getDefaultSharedPreferences(context)
        val fgColor = prefMgr.getInt(context.getString(R.string.pref_key_fg), 0xFFFFFF)
        val bgColor = prefMgr.getInt(context.getString(R.string.pref_key_bg), 0x000000)

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (i in 0 until appWidgetIds.size) {

            val tasks = StringBuilder()

            MainPresenter().getTodos().forEach { tasks.append(it.widgetText()) }

            val appWidgetId = appWidgetIds[i]
            val views = RemoteViews(context.packageName, R.layout.widget_home)
            views.setTextViewText(R.id.textView_widget, tasks.toString())

            views.setInt(R.id.layout_widget, "setBackgroundColor", bgColor)
            views.setInt(R.id.textView_widget, "setTextColor", fgColor)

            // Set onclick
            val configIntent = Intent(context, MainActivity::class.java)
            val configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0)
            views.setOnClickPendingIntent(R.id.layout_widget, configPendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

