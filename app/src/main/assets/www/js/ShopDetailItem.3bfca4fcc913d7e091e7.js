webpackJsonp([11],{207:function(t,e,i){t.exports=i.p+"static/img/detail.jpeg"},225:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=i(48),l=i.n(o),s=i(206);e.default={name:"shop_detail_item",components:{},data:function(){return{scrollHeight:this.isMobile?20:32,scroll_right:[],list_left:["红包","汉堡小子","汉堡类","养生粥","小吃类","手抓饼","奶茶类","果汁类","甜品类","冰品类","套餐类","汤","主食","酒水","老板娘","川菜","健康时蔬","包子","油条","烤猪"],list_right:[],foot_index:0,needChange:!0}},methods:{toFood:function(t,e){var i=this;console.log("我点击了");var o=this.scroll_right[e].el;this.needChange=!1,this.meunScrollR.scrollToElement(o,200),setTimeout(function(){i.foot_index=e,i.needChange=!0},300)},add:function(t,e){console.log(e),this.$parent.$parent.addToCar(t,e,"add")},minus:function(t,e){this.$parent.$parent.addToCar(t,e)},_initScrollData:function(){for(var t=this.$refs.wrapper_right.getElementsByClassName("foods_item"),e=0,i=0;i<t.length;i++)e+=t[i].scrollHeight,this.scroll_right.push({index:i,el:t[i],top:e-t[i].scrollHeight,bottom:e})},_initScrollArea:function(){var t=this;this.meunScrollL=new s.a(this.$refs.wrapper_left,{click:!1,probeType:3}),this.meunScrollR=new s.a(this.$refs.wrapper_right,{click:!1,probeType:3}),this.meunScrollL.on("scroll",function(e){e.y>0&&t.$parent.$parent._enable()}),this.meunScrollR.on("scroll",function(e){e.y>0&&t.$parent.$parent._enable();for(var i=Math.abs(parseInt(e.y)),o=0;o<t.scroll_right.length;o++)i>=t.scroll_right[o].top&&i<t.scroll_right[o].bottom&&t.needChange&&(t.foot_index=t.scroll_right[o].index)})},_disable:function(){this.meunScrollR.disable(),this.meunScrollL.disable()},_enable:function(){this.meunScrollR.enable(),this.meunScrollL.enable()}},watch:l()({foot_index:function(t){this.meunScrollL.scrollToElement(this.$refs.wrapper_left.getElementsByClassName("menu_item")[t],800,0,-100)}},"$store.state.common.noScroll",function(t){t?this._enable():this._disable()}),mounted:function(){for(var t=this,e=0;e<this.list_left.length;e++){for(var o=[],l=1;l<8;l++)o.push({img:i(359)("./"+l+".jpeg"),title:"月销321份，好评率100%",name:"炸鸡，啤酒",price:"<font class='fn-10'>￥</font>3.5",count:0,id:l});this.list_right.push({title:this.list_left[e],list:o})}this.$nextTick(function(){t._initScrollArea()}),setTimeout(function(){t._initScrollData()},200)}}},235:function(t,e,i){e=t.exports=i(193)(!1),e.push([t.i,'.shop_detail_item .scroll[data-v-16d45cec]{color:#7a7e83;display:-webkit-box;display:-ms-flexbox;display:flex}.shop_detail_item .scroll .left[data-v-16d45cec]{-webkit-box-flex:0;-ms-flex:0 0 85px;flex:0 0 85px}.shop_detail_item .scroll .left .current[data-v-16d45cec]{background-color:#fff!important;color:#303030}.shop_detail_item .scroll .left li[data-v-16d45cec]{height:40px;color:#7a7e83;-webkit-box-pack:center;-ms-flex-pack:center;justify-content:center}.shop_detail_item .scroll .right[data-v-16d45cec]{background:#fff;-webkit-box-flex:1;-ms-flex:1;flex:1}.shop_detail_item .scroll .right .foods_item[data-v-16d45cec]{min-height:140px;color:#7a7e83;padding:10px}.shop_detail_item .scroll .right .foods_item .title[data-v-16d45cec]{display:-webkit-box;display:-ms-flexbox;display:flex;font-size:12px;border-bottom:1px solid #e5e5e5;height:28px;-webkit-box-align:center;-ms-flex-align:center;align-items:center}.shop_detail_item .scroll .right .foods_item .title .biaoti[data-v-16d45cec]{color:#fff;font-size:12px;padding:0 2px;margin-right:5px;border-radius:2px}.shop_detail_item .scroll .right .foods_item .item_detail[data-v-16d45cec]{min-height:100px;display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-align:center;-ms-flex-align:center;align-items:center;border-bottom:1px solid #e5e5e5}.shop_detail_item .scroll .right .foods_item .item_detail .h_img[data-v-16d45cec]{width:80px;height:80px;border:none;background-size:100% 100%}.shop_detail_item .scroll .right .foods_item .item_detail .content[data-v-16d45cec]{-webkit-box-flex:1;-ms-flex:1;flex:1;height:100%;padding-left:5px}.shop_detail_item .scroll .right .foods_item .item_detail .content .d_title[data-v-16d45cec]{font-size:14px;color:#303030;margin-bottom:5px}.shop_detail_item .scroll .right .foods_item .item_detail .content .down[data-v-16d45cec]{display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-pack:justify;-ms-flex-pack:justify;justify-content:space-between;-webkit-box-align:center;-ms-flex-align:center;align-items:center;margin-top:5px;padding-right:8px}.shop_detail_item .scroll .right .foods_item .item_detail .content .down .price[data-v-16d45cec]{color:#f86365;font-size:14px}.shop_detail_item .scroll .wrapper[data-v-16d45cec]{position:relative;bottom:0;overflow:hidden}.shop_detail_item .scroll .wrapper ul[data-v-16d45cec]{list-style-type:none;padding:0;margin:0;padding-bottom:10px}.shop_detail_item .scroll .wrapper ul[data-v-16d45cec]:after{display:block;content:"";height:110px}.shop_detail_item .scroll .wrapper ul .menu_item[data-v-16d45cec]{color:#666;min-height:50px;border:none;border-bottom:.5px solid #e5e5e5;display:-webkit-box;display:-ms-flexbox;display:flex;width:100%;text-align:left;-webkit-box-align:center;-ms-flex-align:center;align-items:center;background-color:#f6f6f6;padding-right:0}',""])},259:function(t,e,i){var o=i(235);"string"==typeof o&&(o=[[t.i,o,""]]),o.locals&&(t.exports=o.locals);i(194)("536991ef",o,!0,{})},300:function(t,e,i){t.exports=i.p+"static/img/1.jpeg"},301:function(t,e,i){t.exports=i.p+"static/img/2.jpeg"},302:function(t,e,i){t.exports=i.p+"static/img/3.jpeg"},303:function(t,e,i){t.exports=i.p+"static/img/4.jpeg"},304:function(t,e,i){t.exports=i.p+"static/img/5.jpeg"},305:function(t,e,i){t.exports=i.p+"static/img/6.jpeg"},306:function(t,e,i){t.exports=i.p+"static/img/7.jpeg"},307:function(t,e,i){t.exports=i.p+"static/img/8.jpeg"},308:function(t,e,i){t.exports=i.p+"static/img/header.jpeg"},325:function(t,e,i){function o(t){i(259)}var l=i(27)(i(225),i(334),o,"data-v-16d45cec",null);t.exports=l.exports},334:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"shop_detail_item"},[i("div",{staticClass:"scroll"},[i("div",{ref:"wrapper_left",staticClass:"wrapper left",style:{height:t.screenHeight-t.scrollHeight+"px"}},[i("ul",{staticClass:"content"},t._l(t.list_left,function(e,o){return i("button",{key:o,staticClass:"menu_item fn-13",class:[t.foot_index==o?"current":""],on:{click:function(e){return t.toFood(e,o)}}},[t._v(t._s(e))])}),0)]),t._v(" "),i("div",{ref:"wrapper_right",staticClass:"wrapper right",style:{height:t.screenHeight-t.scrollHeight+"px"}},[i("ul",{staticClass:"content"},t._l(t.list_right,function(e,o){return i("div",{key:o,staticClass:"foods_item"},[i("span",{staticClass:"title"},[i("font",{staticClass:"biaoti primary_bg"},[t._v(t._s(e.title))])],1),t._v(" "),t._l(e.list,function(e,o){return i("div",{key:o,staticClass:"item_detail"},[i("button",{staticClass:"h_img",style:{"background-image":"url("+e.img+")"},on:{click:function(i){return i.stopPropagation(),t.$parent.$parent.showDetail(e)}}}),t._v(" "),i("div",{staticClass:"content"},[i("span",{staticClass:"d_title"},[t._v(t._s(e.name))]),i("br"),t._v(" "),i("span",[t._v(t._s(e.title))]),t._v(" "),i("div",{staticClass:"down"},[i("span",{staticClass:"price",domProps:{innerHTML:t._s(e.price)}}),t._v(" "),i("div",{staticClass:"add_content"},[i("div",{staticStyle:{transition:"all .25s linear"},style:{width:e.count>0?"62px":"0px"}},[i("div",{staticStyle:{height:"25px",overflow:"hidden"}},[i("mt-button",{staticClass:"add_button",attrs:{type:"primary",plain:""},on:{click:function(i){return i.stopPropagation(),t.$parent.$parent.addToCar(i,e)}}},[t._v("-")]),t._v(" "),i("span",{staticClass:"fn-14",domProps:{innerHTML:t._s(e.count)}})],1)]),t._v(" "),i("mt-button",{staticClass:"add_button",attrs:{type:"primary"},on:{click:function(i){return i.stopPropagation(),t.$parent.$parent.addToCar(i,e,"add")}}},[t._v("+")])],1)])])])})],2)}),0)])])])},staticRenderFns:[]}},359:function(t,e,i){function o(t){return i(l(t))}function l(t){var e=s[t];if(!(e+1))throw new Error("Cannot find module '"+t+"'.");return e}var s={"./1.jpeg":300,"./2.jpeg":301,"./3.jpeg":302,"./4.jpeg":303,"./5.jpeg":304,"./6.jpeg":305,"./7.jpeg":306,"./8.jpeg":307,"./detail.jpeg":207,"./header.jpeg":308};o.keys=function(){return Object.keys(s)},o.resolve=l,t.exports=o,o.id=359}});