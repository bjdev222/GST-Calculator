package com.wolcnore.gstbcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;

import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity {

    TextView gstamt,totalamt,igst,cgst,sgst;

    EditText amt,gst;
    String share_text;

    Button adbtn,rmbtn,clear,share,menubtn;

    NativeAdLayout nativeAdLayout;
    NativeAd nativeAd;
    private LinearLayout adView;
    private InterstitialAd interstitialAd;


    int y;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation

        setContentView(R.layout.activity_main);
        gstamt=findViewById(R.id.gstamt);
        totalamt=findViewById(R.id.finalamt);
        amt=findViewById(R.id.amt);
        share=findViewById(R.id.share);

        gst=findViewById(R.id.gst);

        adbtn=findViewById(R.id.adbtn);
        rmbtn=findViewById(R.id.rmbtn);
        clear=findViewById(R.id.clear);

        igst=findViewById(R.id.igst);
        cgst=findViewById(R.id.cgst);
        sgst=findViewById(R.id.sgst);

        menubtn=findViewById(R.id.info);





        // sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "a");

        sequence.setConfig(config);

        sequence.addSequenceItem(adbtn,
                "Add Gst by this button", "GOT IT");
        sequence.addSequenceItem(rmbtn,
                "Remove Gst by this button", "GOT IT");

        sequence.addSequenceItem(clear,
                "Clear Gst by this button", "GOT IT");
        sequence.addSequenceItem(share,
                "Share Gst by this button", "GOT IT");


        sequence.start();

        //Ads
        AudienceNetworkAds.initialize(this);
        //native



        adbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adbtn.setBackgroundColor(getResources().getColor(R.color.Onclickk));
                rmbtn.setBackgroundColor(getResources().getColor(R.color.Button));
                clear.setBackgroundColor(getResources().getColor(R.color.Button));
                share.setBackgroundColor(getResources().getColor(R.color.Button));


                checkValue();
                if(y==1) {
                    double x = Double.parseDouble(amt.getText().toString());
                    double y = Double.parseDouble(gst.getText().toString());


                    DecimalFormat decimalFormatter = new DecimalFormat("##.############");
                    decimalFormatter.setMinimumFractionDigits(2);
                    decimalFormatter.setMaximumFractionDigits(15);

                    //sum these two numbers
                    double z = ((x * y) / 100);

                    double z1=z/2;
                    gstamt.setText("" + BigDecimal.valueOf(z).toPlainString());
                    BigDecimal gstamt2= new BigDecimal(gstamt.getText().toString());

                    double finala = z + x;

                    //totalamt.setText("" +BigDecimal.valueOf(finala).toPlainString());

                    totalamt.setText(decimalFormatter.format(finala));
                    BigDecimal totalamt2= new BigDecimal(totalamt.getText().toString());


                    igst.setText(decimalFormatter.format(z));
                    BigDecimal igst2= new BigDecimal(igst.getText().toString());
                    cgst.setText(decimalFormatter.format(z1));
                   BigDecimal cgst2= new BigDecimal(cgst.getText().toString());
                    sgst.setText(decimalFormatter.format(z1));
                    BigDecimal sgst2= new BigDecimal(sgst.getText().toString());


                     share_text="Amount=="+amt.getText().toString()+"\nGST=="+gst.getText().toString()+
                             "\n\nGST Amount=="+gstamt2+"\nTotal Amount=="+totalamt2+"\n\nIGST=="+igst2+"\nCGST=="+cgst2+"\nSGST=="+sgst2;
                }
                else{
                    Toast.makeText(MainActivity.this, "Enter Details Carefully", Toast.LENGTH_SHORT).show();


                }




            }




        });

        rmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                rmbtn.setBackgroundColor(getResources().getColor(R.color.Onclickk));
                adbtn.setBackgroundColor(getResources().getColor(R.color.Button));
                clear.setBackgroundColor(getResources().getColor(R.color.Button));
                share.setBackgroundColor(getResources().getColor(R.color.Button));

                checkValue();
                if(y==1) {

                    DecimalFormat decimalFormatter = new DecimalFormat("##.####");
                    decimalFormatter.setMinimumFractionDigits(2);
                    decimalFormatter.setMaximumFractionDigits(4);


                    double x = Double.parseDouble(amt.getText().toString());
                    double y = Double.parseDouble(gst.getText().toString());

                    //sum these two numbers
                    double z = x - (x * (100 / (100 + y)));
                    double z1=z/2;

                    gstamt.setText(decimalFormatter.format(z));
                    BigDecimal gstamt2= new BigDecimal(gstamt.getText().toString());


                    double finala = x - z;
                    totalamt.setText(decimalFormatter.format(finala));
                    BigDecimal totalamt2= new BigDecimal(totalamt.getText().toString());


                    igst.setText(decimalFormatter.format(z));
                    BigDecimal igst2= new BigDecimal(igst.getText().toString());
                    cgst.setText(decimalFormatter.format(z1));
                    BigDecimal cgst2= new BigDecimal(cgst.getText().toString());
                    sgst.setText(decimalFormatter.format(z1));
                    BigDecimal sgst2= new BigDecimal(sgst.getText().toString());

                    share_text="Amount=="+amt.getText().toString()+"\nGST=="+gst.getText().toString()+
                            "\n\nGST Amount=="+gstamt2+"\nTotal Amount=="+totalamt2+"\n\nIGST=="+igst2+"\nCGST=="+cgst2+"\nSGST=="+sgst2;

                }
                else{
                    Toast.makeText(MainActivity.this, "Enter Details Carefully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear.setBackgroundColor(getResources().getColor(R.color.Onclickk));
                rmbtn.setBackgroundColor(getResources().getColor(R.color.Button));
                adbtn.setBackgroundColor(getResources().getColor(R.color.Button));
                share.setBackgroundColor(getResources().getColor(R.color.Button));


                amt.setText("");
                gst.setText("");

                gstamt.setText("");
                totalamt.setText("");

                igst.setText("");
                cgst.setText("");
                sgst.setText("");


            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                share.setBackgroundColor(getResources().getColor(R.color.Onclickk));
                rmbtn.setBackgroundColor(getResources().getColor(R.color.Button));
                clear.setBackgroundColor(getResources().getColor(R.color.Button));
                adbtn.setBackgroundColor(getResources().getColor(R.color.Button));

                Toast.makeText(MainActivity.this, "Share GST Calculation", Toast.LENGTH_SHORT).show();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,share_text);
                startActivity(Intent.createChooser(sharingIntent, "Share GST Calculation"));


            }
        });





        //INterstitial facebook
        //facebook audience
        AudienceNetworkAds.initialize(this);
        //interstitial
        interstitialAd = new InterstitialAd(this, "1258758284477521_1259736024379747");
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed

                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback

            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());






        //native
        nativeAdLayout=findViewById(R.id.nativeAdLayout);

        nativeAd = new NativeAd(this, "1258758284477521_1259735914379758");

        NativeAdListener nativeAdListener = new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                // Inflate Native Ad into Container
                inflateAd(nativeAd);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

        };

        // Request an ad
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }

    private void inflateAd(NativeAd nativeAd) {

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.

        nativeAdLayout = findViewById(R.id.nativeAdLayout);

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView = (LinearLayout) inflater.inflate(R.layout.custom_layout_native_ad, nativeAdLayout, false);
        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.custom_layout_native_ad, nativeAdLayout, false);


        nativeAdLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(MainActivity.this, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.info,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                Intent intent=new Intent(MainActivity.this,info.class);
                startActivity(intent);
                break;

            case R.id.back:
                // Toast.makeText(getApplicationContext(), "back", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void checkValue() {
        String s=amt.getText().toString();
        String s2=gst.getText().toString();

        if(s.equals("") || s2.equals(""))
        {
            y=0;

        }
        else {
            y=1;

        }
    }
}