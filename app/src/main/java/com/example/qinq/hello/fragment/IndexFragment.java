package com.example.qinq.hello.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qinq.hello.R;

/**
 * Created by qinqiang on 2015/8/27.
 */
public class IndexFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_index, container, false);
    }
    /**
     * 加载推荐的餐店
     */
    private void loadRecommandRes(RecommandResType type, List<Restaurant> res) {

        if (recommendResHight * recommendResWidth == 0) {
            BitmapDrawable drawable = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.default_img_80_80);
            recommendResHight = drawable.getBitmap().getHeight();
            recommendResWidth = drawable.getBitmap().getWidth();
        }

        // 添加推荐餐店
        View view = LayoutInflater.from(getHostActivity()).inflate(
                R.layout.index_recommend_item, null);// 行ITEM
        view.setId(type.hashCode());
        ImageView iconView = (ImageView) view.findViewById(R.id.iri_iconView);
        TextView titleView = (TextView) view.findViewById(R.id.iri_titleView);
        TextView descView = (TextView) view.findViewById(R.id.iri_descView);

        descView.setVisibility(View.GONE);

        switch (type) {
            case NEARBY:
                iconView.setImageResource(R.drawable.list_icon_near);
                titleView.setText("附近");
                titleView.setTextColor(Color.parseColor("#ffa73c"));
                descView.setText("美好在近旁");
                break;
            case POPULAR:
                iconView.setImageResource(R.drawable.list_icon_hot);
                titleView.setText("人气");
                titleView.setTextColor(Color.parseColor("#ff5385"));
                descView.setText("就住浓烈处");
                break;
            case VISITOR:
                iconView.setImageResource(R.drawable.list_icon_been);
                titleView.setText("去过");
                titleView.setTextColor(Color.parseColor("#ffa73c"));
                descView.setText("让回忆重现");
                break;
            default:
                break;
        }

        LinearLayout hotelLayout = (LinearLayout) view
                .findViewById(R.id.iri_hotelLayout);

        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        llp.gravity = Gravity.CENTER;
        llp.weight = 1;
        llp.leftMargin = 5;
        llp.rightMargin = 5;

        LinearLayout.LayoutParams gapLLP = new LinearLayout.LayoutParams(2,
                LinearLayout.LayoutParams.MATCH_PARENT);
        gapLLP.topMargin = 5;
        gapLLP.bottomMargin = 5;

        if (res.size() == 0) {
            TextView textView = new TextView(getHostActivity());
            textView.setTextColor(Color.parseColor("#333333"));
            textView.setText("暂无");
            textView.setGravity(Gravity.CENTER);
            TextPaint tp = textView.getPaint();
            tp.setFakeBoldText(true);
            hotelLayout.addView(textView, llp);
        }

        int currIndex = 0;
        for (final Restaurant restaurant : res) {
            // View subView = LayoutInflater.from(getHostActivity()).inflate(
            // R.layout.index_recommend_subitem, null);// 行内后面小ITEM
            // ImageView hotelIconView = (ImageView) subView
            // .findViewById(R.id.irs_hotelIconView);
            // hotelIconView.getLayoutParams().width = recommendResWidth;
            // hotelIconView.getLayoutParams().height = recommendResHight;
            // TextView hotelNameView = (TextView) subView
            // .findViewById(R.id.irs_hotelNameView);
            // CommonUtil.loadImg(hotelIconView,
            // mApp.getDefaultOption(R.drawable.default_img_80_80),
            // Command.SERVICE_URL + restaurant.getHeadIcon());
            // hotelNameView.setText(restaurant.getName());

            TextView subView = (TextView) LayoutInflater
                    .from(getHostActivity()).inflate(
                            R.layout.index_recommend_newsubitem, null);
            subView.setText(restaurant.getName());

            if (currIndex > 0 && currIndex < res.size()) {
                View gapView = new View(getHostActivity());
                gapView.setBackgroundColor(Color.parseColor("#dddedf"));
                hotelLayout.addView(gapView, gapLLP);
            }

            subView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent();
                    intent.setClass(getHostActivity(),
                            RestaurantDetailActivity.class);
                    intent.putExtra(Constant.BUNDLE_RESATAURANT, restaurant);
                    startActivity(intent);
                }
            });

            hotelLayout.addView(subView, llp);

            currIndex++;
            if (currIndex >= 4) {
                break;
            }
        }

        if (recommendLayout.getChildCount() > 0) {
            LinearLayout.LayoutParams vGapLLP = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 2);
            vGapLLP.leftMargin = 20;
            vGapLLP.rightMargin = 20;
            vGapLLP.topMargin = 3;
            vGapLLP.bottomMargin = 3;



            View gapView = new View(getHostActivity());
            gapView.setId(type.hashCode()+1);
            gapView.setBackgroundColor(Color.parseColor("#dddedf"));
            recommendLayout.addView(gapView, vGapLLP);

        }

        recommendLayout.addView(view);
    }

}
