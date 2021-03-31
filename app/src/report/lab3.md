# Цели

- Ознакомиться с методом обработки жизненного цикла activity/fragment при помощи Lifecycle-Aware компонентов
- Изучить основные возможности навигации внутри приложения: создание новых activity, navigation graph

## Вариант 16

## Задача 1 - Обработка жизненного цикла с помощью Lifecycle-Aware компонентов

На первом шаге выполнения мы настраивали среду, скопировали проект в нашу студию и применили нужные конфигурации.
На втором шаге мы применили знакомый нам по прочитанной лекции ViewModel для сохранения состояния,
посмотрели, как классы обращаются к ViewModel, а так же использовали его для сохранения состояния нашего Хронометра.
На третьем шаге мы реализовали хронометр непосредственно в классе ViewModel, хранящем данные в LiveData,
с помощью которого мы можем следить за хронометром.
На четвёртом шаге мы при помощи LocationManagera на изменения состояния владельца и  аннотиации @OnLifecycleEvent 
создали приложение, которые отображают текущие координаты нашего устройства.
На пятом шаге мы связывали разные фрагменты. 
А на шестом шаге рассмотрели то, как сохраняются состояния во ViewModel и научились сохранять в процессе восстановления.

## Задача 2 - Навигация (startActivityForResult)

Создаем Activity и к ним xml-файлы.

__Листинг 2.1 - Activity1__

    package com.example.lab3
    
    import android.content.Intent
    import android.os.Bundle
    import android.view.MenuItem
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    import com.google.android.material.navigation.NavigationView
    import kotlinx.android.synthetic.main.activity1.*
    
    class Activity1: AppCompatActivity() {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity1)
    
            nav_view.setNavigationItemSelectedListener {
                startActivity(Intent(this, ActivityAbout::class.java))
                return@setNavigationItemSelectedListener true
            }
        }
    
        fun toSecond(view: View) {
            startActivity(Intent("com.example.lab3.Activity2"))
        }
    
    
    }

__Листинг 2.2 - Activity2__

    package com.example.lab3
    
    import android.app.Activity
    import android.content.Intent
    import android.os.Bundle
    import android.view.MenuItem
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    
    import kotlinx.android.synthetic.main.activity2.nav_view
    
    class Activity2: AppCompatActivity() {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity2)
            nav_view.setNavigationItemSelectedListener {
                startActivity(Intent(this, ActivityAbout::class.java))
                return@setNavigationItemSelectedListener true
            }
        }
    
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == PICK_CONTACT_REQUEST) {
                if (resultCode == Activity.RESULT_OK) {
                    finish()
                }
            }
        }
    
        val PICK_CONTACT_REQUEST = 0
    
        fun toFirst(view: View) {
            finish()
        }
        fun toThird(view: View) {
            val intent = Intent(this, Activity3::class.java)
            startActivityForResult(intent, PICK_CONTACT_REQUEST)
        }
        
    }

__Листинг 2.3 - Activity3__

    package com.example.lab3
    
    import android.app.Activity
    import android.content.Intent
    import android.os.Bundle
    import android.view.MenuItem
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    import kotlinx.android.synthetic.main.activity3.nav_view
    
    class Activity3: AppCompatActivity() {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity3)
            nav_view.setNavigationItemSelectedListener {
                startActivity(Intent(this, ActivityAbout::class.java))
                return@setNavigationItemSelectedListener true
            }
        }
    
        fun toSecondOnThird(view: View) {
            finish()
        }
    
        fun toFirstOnThird(view: View) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    
    }

__Листинг 2.4 - activity1.xml__

    <?xml version="1.0" encoding="utf-8"?>
    <androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:openDrawer="start">
    
    
        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">
    
            <Button
                android:id="@+id/button"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="50dp"
                android:layout_gravity="center|top"
                android:text="to second"
                android:onClick="toSecond" />
    
        </FrameLayout>
    
        <com.google.android.material.navigation.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:fitsSystemWindows="true"
            app:menu="@menu/menu_task2">
    
        </com.google.android.material.navigation.NavigationView>
    
    </androidx.drawerlayout.widget.DrawerLayout>

__Листинг 2.5 - activity2.xml__

    <?xml version="1.0" encoding="utf-8"?>
    <androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:openDrawer="start">
    
    
        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">
    
            <Button
                android:id="@+id/button2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:onClick="toFirst"
                android:text="to first"
                android:layout_marginTop="50dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
    
            <Button
                android:id="@+id/button3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:onClick="toThird"
                android:layout_marginTop="50dp"
                android:text="to third"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/button2" />
        </androidx.constraintlayout.widget.ConstraintLayout>
    
        <com.google.android.material.navigation.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            app:menu="@menu/menu_task2" >
    
    
        </com.google.android.material.navigation.NavigationView>
    
    </androidx.drawerlayout.widget.DrawerLayout>

__Листинг 2.6 - activity3.xml__

    <?xml version="1.0" encoding="utf-8"?>
    <androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:openDrawer="start">
    
        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">
    
            <Button
                android:id="@+id/button2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="50dp"
                android:onClick="toFirstOnThird"
                android:text="@string/to_first"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
    
            <Button
                android:id="@+id/button3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="50dp"
                android:onClick="toSecondOnThird"
                android:text="@string/to_second"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/button2" />
        </androidx.constraintlayout.widget.ConstraintLayout>
    
        <com.google.android.material.navigation.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            app:menu="@menu/menu_task2" >
    
        </com.google.android.material.navigation.NavigationView>
    
    </androidx.drawerlayout.widget.DrawerLayout>
    
__Листинг 2.7 - ActivityAbout__

    package com.example.lab3
    
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    
    class ActivityAbout : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_about)
        }
    }

__Листинг 2.8 - activity_about.xml__

    <?xml version="1.0" encoding="utf-8"?>
    <androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical" android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:id="@+id/textView3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/text_about"
            android:gravity="center"/>
    </androidx.drawerlayout.widget.DrawerLayout>

Прописываем все Activty в Манифесте. Если не прописать, то приложение попросту закроется, когда мы попытаемся перейти на какой-либо экран.

__Листинг 2.9 - AndroidManifest__

    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        package="com.example.lab3">
    
        <application
            android:allowBackup="true"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:roundIcon="@mipmap/ic_launcher_round"
            android:supportsRtl="true"
            android:theme="@style/AppTheme">
            <activity android:name=".ActivityAbout">
                <intent-filter>
                    <action android:name="com.example.lab3.ActivityAbout" />
                    <category android:name="android.intent.category.DEFAULT" />
                </intent-filter>
            </activity>
            <activity android:name=".Activity1">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
            <activity android:name=".Activity2">
                <intent-filter>
                    <action android:name="com.example.lab3.Activity2" />
                    <category android:name="android.intent.category.DEFAULT" />
                </intent-filter>
            </activity>
            <activity android:name=".Activity3">
                <intent-filter>
                    <action android:name="com.example.lab3.Activity3" />
                    <category android:name="android.intent.category.DEFAULT" />
                </intent-filter>
            </activity>
        </application>
    
    </manifest>

## Задача 3 - Навигация (флаги Intent/атрибуты Activity)

По нажатию на кнопку будем не возвращаться назад, а открывать новую Activity,
но с флагом __FLAG_ACTIVITY_CLEAR_TOP__
Этот флаг ищет в таске создаваемое Activity. Если находит, то открывает, а все, что выше – закрывает.

__Листинг 3.1 - измененная Activity3__

    package com.example.lab3
    
    import android.app.Activity
    import android.content.Intent
    import android.os.Bundle
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    import kotlinx.android.synthetic.main.activity3.nav_view
    
    class Activity3: AppCompatActivity() {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity3)
            nav_view.setNavigationItemSelectedListener {
                startActivity(Intent(this, ActivityAbout::class.java))
                return@setNavigationItemSelectedListener true
            }
        }
    
        fun toSecondOnThird(view: View) {
            finish()
        }
    
        fun toFirstOnThird(view: View) {
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    
    }
    
__Листинг 3.2 - измененная Activity2__

    package com.example.lab3
    
    import android.app.Activity
    import android.content.Intent
    import android.os.Bundle
    import android.view.MenuItem
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    
    import kotlinx.android.synthetic.main.activity2.nav_view
    
    class Activity2: AppCompatActivity() {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity2)
            nav_view.setNavigationItemSelectedListener {
                startActivity(Intent(this, ActivityAbout::class.java))
                return@setNavigationItemSelectedListener true
            }
        }
    
        fun toFirst(view: View) {
            finish()
        }
    
        fun toThird(view: View) {
            startActivity(Intent(this, Activity3::class.java))
        }
    
    }

## Задача 4 - Навигация (флаги Intent/атрибуты Activity) - расширенная

Добавим переход из Activity1 в Activity3 и будем запускать Activity с флагом __FLAG_ACTIVITY_NO_HISTORY__,
который не даст сохраниться открытой Activity в бэкстеке.
Также для наилучшей демонстрации в Activity3 при переходе на второй экран будем не закрывать Activity, а открывать новую.
В итоге наша навигация будет работать так:
перейдём из первого экрана на третий, с третьего на второй,
нажмем кнопку назад и окажемся снова на первом экране, пропустив третий.

__Листинг 3.1 - измененная Activity1__

    package com.example.lab3
    
    import android.content.Intent
    import android.os.Bundle
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    import kotlinx.android.synthetic.main.activity1.*
    
    class Activity1_task4: AppCompatActivity() {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity1_task4)
    
            nav_view.setNavigationItemSelectedListener {
                startActivity(Intent(this, ActivityAbout::class.java))
                return@setNavigationItemSelectedListener true
            }
        }
    
        fun toSecond_4(view: View) {
            startActivity(Intent("com.example.lab3.Activity2_task4"))
        }
    
        fun toThird_4(view: View) {
            val intent = Intent(this, Activity3_task4::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
        }
        
    }

__Листинг 3.2 - измененная Activity3__

    package com.example.lab3
    
    import android.app.Activity
    import android.content.Intent
    import android.os.Bundle
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    import kotlinx.android.synthetic.main.activity3.nav_view
    
    class Activity3_task4: AppCompatActivity() {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity3_task4)
            nav_view.setNavigationItemSelectedListener {
                startActivity(Intent(this, ActivityAbout::class.java))
                return@setNavigationItemSelectedListener true
            }
        }
    
        fun toSecondOnThird_4(view: View) {
            startActivity(Intent(this, Activity2_task4::class.java))
        }
    
        fun toFirstOnThird_4(view: View) {
            val intent = Intent(this, Activity1_task4::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    
    }
    

## Задача 5 - Навигация (Fragments, Navigation Graph)



__Листинг 5.1 - Main__

    package com.example.lab3.task5
    
    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import androidx.navigation.Navigation
    import androidx.navigation.ui.setupWithNavController
    import com.example.lab3.R
    import kotlinx.android.synthetic.main.task5_main.*
    
    class Main: AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.task5_main)
            val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
            nav_view.setupWithNavController(navController)
        }
    
    }

__Листинг 5.2 - Fragment1__

    package com.example.lab3.task5
    
    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.navigation.Navigation
    import com.example.lab3.R
    import kotlinx.android.synthetic.main.fragment_task5_first.view.*
    
    class Fragment1 : Fragment() {
    
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_task5_first, container, false)
            view.to2on1.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_fragment1_to_fragment2)
            }
            view.to3on1.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_fragment1_to_fragment3)
            }
            return view
        }
    }

__Листинг 5.3 - Fragment2__

    package com.example.lab3.task5
    
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import androidx.navigation.Navigation
    import com.example.lab3.R
    import kotlinx.android.synthetic.main.fragment_task5_second.view.*
    
    class Fragment2 : Fragment() {
    
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_task5_second, container, false)
            view.to1on2.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_fragment2_to_fragment1)
            }
            view.to3on2.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_fragment2_to_fragment3)
            }
            return view
        }
    }

__Листинг 5.4 - Fragment3__

    package com.example.lab3.task5
    
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import androidx.navigation.Navigation
    import com.example.lab3.R
    import kotlinx.android.synthetic.main.fragment_task5_third.view.*
    
    class Fragment3 : Fragment() {
    
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_task5_third, container, false)
            view.to1on3.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_fragment3_to_fragment1)
            }
            view.to2on3.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_fragment3_to_fragment2)
            }
            return view
        }
    }


__Листинг 5.5 - task5_main.xml__

    <?xml version="1.0" encoding="utf-8"?>
    <androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <fragment
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph"
        />
    
    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:menu="@menu/menu_task2"/>
    
    </androidx.drawerlayout.widget.DrawerLayout>

__Листинг 5.6 - nav_graph.xml__

    <?xml version="1.0" encoding="utf-8"?>
    <navigation xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto" android:id="@+id/nav_graph"
        app:startDestination="@id/fragment1">
    
        <fragment
            android:id="@+id/fragment1"
            android:name="com.example.lab3.task5.Fragment1"
            android:label="Fragment1" >
            <action
                android:id="@+id/action_fragment1_to_fragment2"
                app:destination="@id/fragment2" />
            <action
                android:id="@+id/action_fragment1_to_fragment3"
                app:destination="@id/fragment3" />
        </fragment>
        <fragment
            android:id="@+id/fragment2"
            android:name="com.example.lab3.task5.Fragment2"
            android:label="Fragment2" >
            <action
                android:id="@+id/action_fragment2_to_fragment1"
                app:destination="@id/fragment1" />
            <action
                android:id="@+id/action_fragment2_to_fragment3"
                app:destination="@id/fragment3" />
        </fragment>
        <fragment
            android:id="@+id/fragment3"
            android:name="com.example.lab3.task5.Fragment3"
            android:label="Fragment3" >
            <action
                android:id="@+id/action_fragment3_to_fragment1"
                app:destination="@id/fragment1" />
            <action
                android:id="@+id/action_fragment3_to_fragment2"
                app:destination="@id/fragment2" />
        </fragment>
        <activity
            android:id="@+id/activityAbout"
            android:name="com.example.lab3.ActivityAbout"
            android:label="ActivityAbout" />
    </navigation>

Переходы работают быстрее и без задержек, в отличие от startActivity,
однако в стеке хранятся абсолютно все переходы.
То есть если мы будем по 100 раз переходить от первого фрагмента во 2 и столько же обратно,
то мы должны будем 200 раз нажать на кнопку back, чтобы вернуться в первоначальное состояние.


## Выводы

Суммарное время выполнения работы - часов 20-30(да, ту мач...) с учетом поиска миллиона возникающих ошибок по глупости,
чтением документации и составлением отчета...
В данной работе мы ознакомились с методом обработки жизненного цикла activity/fragment при помощи Lifecycle-Aware компонентов
и изучили основные возможности навигации внутри приложения:
создание новых activity, navigation graph.
Насчет удобства и моего выбора реализации навигации - вопрос сомнительный...
С одной стороны, реализовывать навигацию удобнее  с помощью Navigation Graph, переходы выполняются быстрее,
чем переходы на Activity, всю навигацию можно посмотреть на графе
и сами переходы с точки зрения кода выглядят более понятно и занимают меньше строк
НОООО
То, что эта штука всегда записывает в стек все переходы, тем самым возврат обратно с помощью кнопки back
может быть просто жестоким испытаниемж :(
Хотя никто не отменял того факта, что решение этой штуки на самом деле существует, просто я недалёкий...