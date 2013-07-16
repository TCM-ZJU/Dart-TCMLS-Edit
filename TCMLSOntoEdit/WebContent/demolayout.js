var pos = Number(window.location.href.substring(window.location.href.lastIndexOf("/")+1, window.location.href.length-5));
var tnpos = pos + 1;
var tppos = pos - 1;

if (tnpos < 10)
  tnpos = String("000" + tnpos)
else if (tnpos < 100)
  tnpos = String("00" + tnpos)
else if (tnpos < 1000)
  tnpos = String("0" + tnpos)
else
  tnpos = String(tnpos)

if (tppos < 10)
  tppos = String("000" + tppos)
else if (tppos < 100)
  tppos = String("00" + tppos)
else if (tppos < 1000)
  tppos = String("0" + tppos)
else
  tppos = String(tppos)


document.write('<div id="demoHead">qooxdoo: <span>The new era of web interface development</span></div>');
document.write('<div id="demoFoot">');

if( /\/showcase\//.test(window.location.href) ) {
  document.write('Selected: Showcase | ');
  document.write('[<a href="../example/index.html">Go to: Example</a>] &#160;');
  document.write('[<a href="../test/index.html">Go to: Test</a>] &#160;');
} else if( /\/example\//.test(window.location.href) ) {
  document.write('Selected: Example | ');
  document.write('[<a href="../showcase/index.html">Go to: Showcase</a>] &#160;');
  document.write('[<a href="../test/index.html">Go to: Test</a>] &#160;');
} else {
  document.write('Selected: Test | ');
  document.write('[<a href="../showcase/index.html">Go to: Showcase</a>] &#160;');
  document.write('[<a href="../example/index.html">Go to: Example</a>] &#160;');
};

if(window.location.href.indexOf("oss.schlund.de") == -1)
{
  document.write("&#160;&#160;&#160;&#160;&#160;-&#160;&#160;&#160;&#160;&#160; &#160;");
  if( /\/source\//.test(window.location.href) ) {
    document.write('[<a href="javascript:void(window.location.href=window.location.href.replace(/\\/source\\//, \'\\/build\\/\'))">Go to: Build</a>] &#160;');
  } else {
    document.write('[<a href="javascript:void(window.location.href=window.location.href.replace(/\\/build\\//, \'\\/source\\/\'))">Go to: Source</a>] &#160;');
  };
};

document.write('</div>');
document.write('<div id="demoDebug"></div>');
document.write('<div id="demoFrame">&#160;</div>');

function showTestFiles()
{
  if( window.location.href.search(/\/showcase\//)+1 ) {
    str = showstr;
  }
  else if( window.location.href.search(/\/example\//)+1 ) {
    str = exastr;
  }
  else if( window.location.href.search(/\/test\//)+1 ) {
    str = teststr;
  }
  else {
    str = "";
  }

  var arr = str.split(" ");
  var p = window.location.pathname.replace(/\\/g, "/");
  var sel = -1;

  document.writeln('<select id="demoFiles" onchange="if(this.options[this.selectedIndex].value)window.location.href=this.options[this.selectedIndex].value">');
  document.writeln('<option value=""></option>');
  for( var i=1; i<arr.length; i++ ) {
    document.write('<option value="' + arr[i] + '"');
    if(p.search(new RegExp( "\/" + arr[i]) )+1) {
      document.write(' selected="selected"');
      sel = i;
    }
    document.writeln('>' + arr[i].replace(/_/g, " ").replace(/\.html/, "") + '</option>');
  }
  document.writeln('</select>');

  if (sel != -1)
  {
    document.writeln('<div id="demoJump">');

    if (sel > 1) {
      document.writeln("<button onclick='window.location.href=\"" + arr[sel-1] + "\"'>&lt;</button>");
    }

    if (sel < arr.length-1) {
      document.writeln("<button onclick='window.location.href=\"" + arr[sel+1] + "\"'>&gt;</button>");
    }

    document.writeln('</div>');
  };
}

document.title = location.href.substring(location.href.lastIndexOf("/")+1).replace(".html", "").replace(/_/g, " ") + " @ qooxdoo :: demo";

if(location.href.indexOf("qooxdoo.oss.schlund.de") != -1)
{
  var cstatus="active", visible="0", page_title="", url_of_counter_file="http://qooxdoo.oss.schlund.de/counter/counter.php", page_url=unescape(location.href), referrer=(document.referrer) ? document.referrer : "", page_title=(page_title.length == 0) ? document.title : page_title;

  document.write("<script type=\"text/javascript\" src=\"");
  document.write(url_of_counter_file + "?chCounter_mode=js&amp;jscode_version=3.1.1&amp;status=" + cstatus + "&amp;visible=" + visible + "&amp;page_title=" + encodeURIComponent( page_title ));
  document.write("&amp;page_url=" + encodeURIComponent( page_url ) + "&amp;referrer=" + encodeURIComponent( referrer ) + "&amp;res_width=" + screen.width + "&amp;res_height=" + screen.height + "\"><" + "/script>");
}

var showstr = " Showcase_1.html";
var exastr = " Atom_1.html Atom_2.html Atom_3.html BarView_1.html BarView_2.html Button_1.html CheckBox_1.html ComboBox_1.html CookieStorage_1.html CookieStorage_2.html Cookie_1.html FieldSet_1.html FieldSet_2.html FieldSet_3.html Fields_1.html Flash_1.html GalleryList_1.html Gallery_1.html Iframe_1.html Inline_1.html ListView_1.html ListView_2.html ListView_3.html ListView_4.html List_1.html Menu_1.html NativeWindow_1.html RadioButton_1.html RepeatButton_1.html Spinner_1.html TabView_1.html ToolBar_1.html ToolBar_2.html ToolBar_3.html ToolBar_4.html ToolTip_1.html Transport_1.html Tree_1.html Window_1.html";
var teststr = " Atom_1.html Atom_2.html Atom_3.html Atom_4.html Atom_5.html Atom_6.html Atom_7.html Atom_8.html Atom_9.html Border_1.html BoxLayout_1.html BoxLayout_2.html Builder_1.html Builder_2.html Builder_3.html Builder_4.html Button_1.html CanvasLayout_1.html CanvasLayout_2.html CanvasLayout_3.html CanvasLayout_4.html CanvasLayout_5.html Clipping_1.html Clone_1.html Clone_2.html ColorTheme_1.html ColorTheme_2.html ColorTheme_3.html ComboBox_1.html ComboBox_2.html ComboBox_3.html Compile_1.html Compile_2.html CrossBrowser_1.html CrossBrowser_2.html DataHandling_1.html DataHandling_2.html DockLayout_1.html DockLayout_2.html DockLayout_3.html DockLayout_4.html DockLayout_5.html DockLayout_6.html DragAndDropManager_1.html DragAndDropManager_2.html EnabledDisabled_1.html FieldSet_1.html Fields_1.html FlowLayout_1.html FlowLayout_2.html FlowLayout_3.html FocusManager_1.html Font_1.html Gallery_2.html Gallery_3.html GridLayout_1.html GridLayout_10.html GridLayout_11.html GridLayout_2.html GridLayout_3.html GridLayout_4.html GridLayout_5.html GridLayout_6.html GridLayout_7.html GridLayout_8.html GridLayout_9.html HorizontalBoxLayout_1.html HorizontalBoxLayout_2.html HorizontalBoxLayout_3.html IconHtml_1.html Image_1.html Image_2.html Image_3.html Image_4.html Label_1.html Label_2.html Label_3.html Label_4.html Leak_1.html Link_1.html ListView_1.html ListView_10.html ListView_2.html ListView_3.html ListView_4.html ListView_5.html ListView_6.html ListView_7.html ListView_8.html ListView_9.html List_1.html List_2.html List_3.html List_4.html Menu_1.html Menu_2.html Node_1.html Performance_1.html Performance_2.html Popups_1.html Popups_2.html ToolBar_1.html ToolBar_2.html ToolBar_3.html ToolBar_4.html Tree_1.html Tree_2.html Tree_3.html Tree_4.html Tree_5.html Tree_6.html Umlauts_1.html UserData_1.html VerticalBoxLayout_1.html VerticalBoxLayout_2.html VerticalBoxLayout_3.html Window_1.html Window_2.html Window_3.html Window_4.html Window_5.html Window_6.html";
showTestFiles();
