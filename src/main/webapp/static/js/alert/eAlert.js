/* * author:ys date:2020-11-05 */
(function($) {
  $.extend ({
    eAlert: function(option) {
      var _this = this;
      var settings = {
        type: 'info', //info,success,warning,error
        content: '提示内容',
        autoClose: true
      };
      var $dom = $ ('.my_alert-wrapper');
      if ($dom.length === 0) {
        $ (document.body).append ('<div class="my_alert-wrapper"></div>');
      }
      $dom = $ ('.my_alert-wrapper');
      $.extend (settings, option);
      var box = $ ('<div class="my_alertBox" animation=""></div>');
      box.addClass ('my_alertBox--' + settings.type);
      var typeIcon = $ ('<i class="my_alert-icon iconfont"></i>');
      typeIcon.addClass ('icon-alert-' + settings.type);
      var contentBox = $ ('<div class="my_alert-content"></div>');
      contentBox.text (settings.content);
      var closeIcon = $ ('<i class="my_alert-closebtn iconfont icon-close"></i>');
      box.append (typeIcon).append (contentBox).append (closeIcon);
      $dom.append (box);
      if (settings.autoClose === true) {
        setTimeout (function() {
          box.remove ();
        }, 3 * 1000);
      }
      closeIcon.on ('click', function() {
        box.remove ();
      });
    }
  });
}) (jQuery);
