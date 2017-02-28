/*
 * Copyright (C) 2016 Christian Schmitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package berlin.volders.badger.example;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import berlin.volders.badger.BadgeShape;
import berlin.volders.badger.Badger;
import berlin.volders.badger.CountBadge;

public class BadgerActivity extends AppCompatActivity {

    CountBadge.Factory ovalFactory;
    CountBadge.Factory squareFactory;
    CountBadge.Factory circleFactory;
    CountBadge.Factory ovalBadgedFactory;
    CountBadge.Factory squareBadgedFactory;
    CountBadge.Factory circleBadgedFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badger);
        ovalFactory = new CountBadge.Factory(this, BadgeShape.oval(1f, 2f, Gravity.BOTTOM));
        squareFactory = new CountBadge.Factory(this, BadgeShape.square(1f, Gravity.NO_GRAVITY, .5f));
        circleFactory = new CountBadge.Factory(this, BadgeShape.circle(.5f, Gravity.END | Gravity.TOP));

        ovalBadgedFactory = new CountBadge.Factory(BadgeShape.oval(1f, 2f, Gravity.BOTTOM)
                , ContextCompat.getColor(this, android.R.color.black)
                , ContextCompat.getColor(this, android.R.color.white)
                , ContextCompat.getColor(this, android.R.color.white));
        squareBadgedFactory = new CountBadge.Factory(BadgeShape.square(1f, Gravity.NO_GRAVITY, .5f)
                , ContextCompat.getColor(this, android.R.color.black)
                , ContextCompat.getColor(this, android.R.color.white)
                , ContextCompat.getColor(this, android.R.color.white));
        circleBadgedFactory = new CountBadge.Factory(BadgeShape.circle(.5f, Gravity.END | Gravity.TOP)
                , ContextCompat.getColor(this, android.R.color.black)
                , ContextCompat.getColor(this, android.R.color.white)
                , ContextCompat.getColor(this, android.R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_badger, menu);
        Badger.sett(menu.findItem(R.id.action_oval), ovalFactory).setCount(0);
        Badger.sett(menu.findItem(R.id.action_square), squareFactory).setCount(0);
        Badger.sett(menu.findItem(R.id.action_circle), circleFactory).setCount(0);
        Badger.sett(menu.findItem(R.id.action_oval_badged), ovalBadgedFactory).setCount(0);
        Badger.sett(menu.findItem(R.id.action_square_badged), squareBadgedFactory).setCount(0);
        Badger.sett(menu.findItem(R.id.action_circle_badged), circleBadgedFactory).setCount(0);

        Badger.sett(menu.findItem(R.id.action_layout), circleBadgedFactory).setCount(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_oval:
            case R.id.action_square:
            case R.id.action_circle:
            case R.id.action_oval_badged:
            case R.id.action_square_badged:
            case R.id.action_circle_badged:
            case R.id.action_layout:
                // factory is not used for getting the badge
                //noinspection ConstantConditions
                CountBadge badge = Badger.sett(item, null);
                badge.setCount(badge.getCount() + 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void resetBadges(View view) {
        invalidateOptionsMenu();
    }
}
