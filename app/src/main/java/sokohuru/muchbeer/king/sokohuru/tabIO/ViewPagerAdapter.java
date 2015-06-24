package sokohuru.muchbeer.king.sokohuru.tabIO;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.io.CharArrayWriter;

import sokohuru.muchbeer.king.sokohuru.Fragment.FragmentSearch;
import sokohuru.muchbeer.king.sokohuru.Fragment.FragmentSoko;

/**
 * Created by muchbeer on 6/22/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfTabs;

    //Build a constructor and assign the passed Value to appropiate value in the class
    public ViewPagerAdapter(FragmentManager fm,
                            CharSequence mTitles[],
                            int mNumbOfTabsSum)  {

                super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs=mNumbOfTabsSum;
    }

    //This method return the fragment for the every position in the View Pager

    @Override
    public Fragment getItem(int position) {
        if(position == 0) // if the position is 0 we are returning the First tab
        {
            FragmentSoko tab1 = new FragmentSoko();
            return tab1;
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            FragmentSearch tab2 = new FragmentSearch();
            return tab2;
        }

    }
    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
