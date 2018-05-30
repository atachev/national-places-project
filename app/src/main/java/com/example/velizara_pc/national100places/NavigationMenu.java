package com.example.velizara_pc.national100places;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NavigationMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout myDrawerLayout;
    public static final String obejectNames [] = {"Велянова къща", "Къща-музей Неофит Рилски", "Музей Никола Вапцаров", "Иконна изложба",
        "Църква Св.Троица", "Връх Вихрен", "Църква Св. Св. Теодор Тирон и Теодор Стратилат", "Градски исторически музей Мелник",
        "Кордопулова къща Мелник", "Светроженски манастир Рождество богородично", "Местност Рупите - храм паметник Света Петка Българска",
        "Национален парк-музей „Самуилова крепост“", "Регионален исторически музей  -Благоевград", "Възрожденски комплекс „Вароша”",
        "Епископска Базилика - Сандански", "Археологически музей-Сандански", "Общински исторически музей-Гоце Делчев", "Археологически музей - Несебър",
        "Музей на солта - Поморие", "Поморийско езеро", "Храм Свети свети Кирил и Методий - Бургас", "Природоз. център Пода",
        "Исторически музей - Малко Търново", "Местност Петрова нива", "Археологически музей - Созопол", "Общински исторически музей - Царево",
        "Регионален исторически музей - Варна", "Военноморски музей - Варна", "Музей на мозайките - Девня", "Царевец", "Арбанаси",
        "Регионален исторически музей - Велико Търново", "Къща музей Алеко Константинов - Свищов", "Къща - музей Иларион Макариополски - Елена",
        "Арх. - исторически комплекс - Даскалоливница - Елена", "Исторически музей - Смолян", "Планетариум - Смолян", "с. Момчиловци",
        "пещера Ухловица", "връх Голям Перелик", "Чудните мостове", "Пещера Шаренка", "Триградско ждрело", "Ягодинска пещера", "Буйновско ждрело",
        "връх Снежанка", "Eтногравски ареален комплекс - Златоград", "Широка Лъка", "Родопски кристал - Мадан", "Исторически музей - Видин",
        "Крепост Баба Вида", "Пещера Магурата", "Исторически музей - Белоградчик", "Белоградчишки скали", "Пещера Леденика",
        "Регионален исторически музей - Враца", "връх Околчица – Лобното място на Христо Ботев", "Археологически комплекс „КАЛЕТО” - Мездра",
        "Национален музей Параход Радецки", "Паметник на Христо Ботев и неговата чета", "Музей на образованието - Габрово",
        "Архитектурно-етнографски комплекс „Етъра”", "местността „Узана” - Габрово", "Дом на хумора и сатирата - Габрово", "Архитектурно-исторически резерват Боженци",
        "музей на резбарското и етнографско изкуство - Трявна", "Дряновски манастир", "Пещера „Бачо Киро“", "Музей „Кольо Фичето“ - Дряново",
        "Художествена галерия - Добрич", "Дом - паметник „Й. Йовков“ - Добрич", "комплекс „Двореца“ - Балчик", "Университетска ботаническа градина - Балчик",
        "Исторически музей Каварна", "археологически резерват „Калиакра”", "Перперикон", "Манастир „Св. Йоан Предтеча“ - Кърджали",
        "Регионален исторически музей - Кърджали", "Худ. галерия „Владимир Димитров – Майстора“ - Кюстендил", "Къща-музей „Димитър Пешев“ - Кюстендил",
        "Музей „Средновековна църква Св. Георги“ - Кюстендил", "Регионален исторически музей - Кюстендил", "Връх Руен", "Музей Рилски манастир",
        "хижа „Скакавица“ и Седемте рилски езера",  "Стобски пирамиди",
        "Музей „Васил Левски“ - Ловеч", "Къкринско ханче", "Национален пещерен дом - Луковит", "Деветашка пещера", "Троянска Света Обител „Усп. Богородично“",
        "Природонаучен музей - с. Черни Осъм", "Музей на народните худ. занаяти и приложните изкуства - Троян", "Исторически музей - Тетевен",
        "пещера „Съева дупка“", "Етнографски музей - Берковица", "Къща музей „Иван Вазов” - Берковица", "връх Ком", "Исторически музей - Чипровци",
        "манастир „Св. Ив. Рилски” - Чипровци", "Къща-музей „Станислав Доспевски” - Пазарджик", "Катедрална църква „Св. Богородица” - Пазарджик",
        "Регионален исторически музей - Пазарджик", "Вила-музей Александър стамболийски", "Историческа местност „Оборище“", "Къща - музей „Райна Княгиня“", "Пещера – пещера „Снежанка“",
        "крепост Перистера - Пещера", "Исторически музей - Батак", "Исторически музей - Велинград", "Исторически музей - Брацигово",
        "Ждрелото на р. Ерма - Трън", "Подземен минен музей - Перник", "Регионален исторически музей - Плевен", "Мавзолей-параклис „Св. Георги Победоносец“ - Плевен",
        "Панорама „Плевенска епопея", "Исторически музей - Пловдив", "Етнографски музей - Пловдив", "Античен театър - Пловдив", "Исторически музей - Перущица",
        "Къща-музей „Иван Вазов“", "Женски метох - Сопот", "Нац. музей „Васил Левски“", "Исторически музей - Карлово", "Нац. музей „Христо Ботев“","Връх Ботев",
        "Асенова крепост", "Исторически музей - Асеновград", "Бачковски манастир", "комплекс „Бунтовна” - с.Кръстевич", "Тракийска гробница - Старосел",
        "Археологически музей - Хисаря", "Исторически музей - Клисура", "Арх. резерват „Абритус“ - Разград", "Исторически музей - Исперих",
        "ИАР „Сборяново“ - Исперих", "Демир Баба теке“ и тракийски град Хелис – с. Свещари", "Пантеон на Възрожденците - Русе",
        "Къща-музей „Захари Стоянов“ - Русе", "Ивановски скални манастири", "Скален Манастир Димитрий Басарбовски", "Манастир Света Марина",
        "Исторически музей - гр. Бяла", "Исторически музей, Меджиди Табия - Силистра", "резерват Сребърна", "Музей на Дунавския риболов - Тутракан",
        "Военна гробница - Тутракан", "Къща-музей Хаджи Димитър - Сливен", "Музей на текстилната индустрия - Сливен", "Художествена галерия - Сливен",
        "Пантеон на Г.С.Раковски - Котел", "Природонаучен музей - Котел", "Къща-музей Йордан Йовков - Жеравна", "музей Каранова могила - Нова Загора",
        "Национален исторически музей", "Национален музей „Боянска черква“", "Храм-паметник „Александър Невски“",
        "Национален военноисторически музей", "Национален музей „Земята и хората“", "Национален дворец на културата", "музей за българско изобразително изкуство",
        "Нац. Етнографски институт с музей", "Национална художествена галерия", "Национален природонаучен музей", "Музей на физическата култура и спорта",
        "Зоологическа градина ", "Национален антропологичен музей", "Археологически институт с музей при БАН", "Национален политехнически музей",
        "Исторически музей - Етрополе", "Часовниковата кула - Етрополе", "манастир „Св. Троица” - Етрополе", "Копривщица – АИР", "Манарстир ”7-те престола” - Осеновлаг",
        "Исторически музей - Самоков", "Девически манастир - Самоков", "Цари Мали Град", "Исторически музей - Ботевград", "Часовникова кула - Ботевград",
        "Къща-музей на Пейо К. Яворов - Чирпан", "Худ. галерия „Никола Манев“ - Чирпан", "манастир „Св. Атанасий“ - Златна ливада",
        "Регионален исторически музей - Стара Загора", "Музей Неолитни жилища - Стара Загора", "Бранителите на Стара Загора 1877 ",
        "Тракийска гробница", "музей „Чудомир“ - Казанлък", "Храм „Рождество Христово“ - Шипка","връх Шипка – Национален парк-музей „Шипка“ - паметник на свободата", "крепост „Мисионис“ - Търговище",
        "Славейковото училище - Търговище", "Монумент „Света Богородица“ - Хасково", "Средновековна крепост с.Мезек", "Тракийска куполна гробница - с.Мезек",
        "Вила Армира", "Общински исторически музей - Ивайловград", "Александровска гробница и музеен център", "Исторически музей - Димитровград",
        "Дом-музей „Пеньо Пенев“", "обсерватория „Джордано Бруно“ - Димитровград", "Рег. исторически музей - Шумен", "ИАР Шуменска крепост",
        "Паметник 1300 г. България - Шумен", "Томбул джамия - Шумен", "Археологически резерват Плиска", "Мадарски конник - с.Мадара",
        "НАР Велики Преслав - Преслав", "Исторически музей - Ямбол", "Античен град Кабиле - Ямбол", "Етнографско-археологически музей - Елхово"};

    public FileInputStream fis = null;
    public FileOutputStream fos = null;
    public DataInputStream dis;
    public DataOutputStream dos;
    public static HashMap<String, Boolean> visitedTable = new HashMap<String, Boolean>();
    public static HashMap<String, Float> ratingTable = new HashMap<String, Float>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        //Intent receveUser = getIntent();
        //String user = receveUser.getStringExtra("user");
        File path = getApplicationContext().getFilesDir();
        File userName = new File(path, LoginActivity.userFileName);
        File ratings = new File(path, "objectRating1");
        try {
            if(userName.length()>0){
                fis = new FileInputStream(userName);
                dis = new DataInputStream(fis);
                while(dis.available()>0){
                    String key = "";
                    int count = dis.readInt();
                    for(int i = 0; i<count; i++)
                        key = key + dis.readChar();
                    visitedTable.put(key, dis.readBoolean());
                }
            } else{
                fos = new FileOutputStream(userName);
                dos = new DataOutputStream(fos);
                for(int i=0; i<obejectNames.length; i++){
                    dos.writeInt(obejectNames[i].length());
                    dos.writeChars(obejectNames[i]);
                    dos.writeBoolean(false);
                    visitedTable.put(obejectNames[i], false);
                }
            }
        } catch (IOException io){
            io.printStackTrace();
        } finally {
            if(dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            if(fis != null){
                try{
                    fis.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            if(dos != null){
                try{
                    dos.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
        }
        try {
            if(ratings.length()>0){
                fis = new FileInputStream(ratings);
                dis = new DataInputStream(fis);
                while(dis.available()>0){
                    String key = "";
                    int count = dis.readInt();
                    for(int i = 0; i<count; i++)
                        key = key + dis.readChar();
                    ratingTable.put(key, dis.readFloat());
                }
            } else{
                fos = new FileOutputStream(ratings);
                dos = new DataOutputStream(fos);
                for(int i=0; i<obejectNames.length; i++){
                    dos.writeInt(obejectNames[i].length());
                    dos.writeChars(obejectNames[i]);
                    dos.writeFloat((float)0.0);
                    ratingTable.put(obejectNames[i], (float) 0.0);
                }
            }
        } catch (IOException io){
            io.printStackTrace();
        } finally {
            if(dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            if(fis != null){
                try{
                    fis.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            if(dos != null){
                try{
                    dos.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
        }
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        myDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.places:
                Intent intent = new Intent(this, Regdrawer.class);
                this.startActivity(intent);
                break;
            case R.id.poseteni:
                Intent poseteni = new Intent(this, VisitedObjects.class);
                this.startActivity(poseteni);
                break;
            case R.id.takePict:
                Intent photoTake = new Intent(this, TakePicture.class);
                this.startActivity(photoTake);
                break;
            case R.id.seePict:
                Intent seePhoto = new Intent(this, ImageGalery.class);
                this.startActivity(seePhoto);
                break;
            case R.id.karta:
                Intent showKarta = new Intent(this, MapsActivity.class);
                this.startActivity(showKarta);
                break;

        }
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                myDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
