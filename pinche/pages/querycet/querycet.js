// pages/querycet/querycet.js
Page({

  /**
   * 页面的初始数据
   */
  
  data: {
    sid:"",
    hasClick:false,
    eNum:"",
  },
  /*
  *取得用户的学号
  *
  */
  sidInput:function(e){
    this.setData({
      sid: e.detail.value
    });
  },
  /**
   * 查询准考证号
   */
  
  getEnum:function(e){
    var that = this;
    if(this.data.hasClick){
      return;
    }
    var sid = this.data.sid;
    this.setData({
      hasClick:true
    });
    wx.request({
      // todo 修改一下地址。。
      url: 'https://',
      method: 'POST',
      header: { 'content-type': 'application/json' },
      data:{
        stunum:sid,
      },
      success: function (res) {

        if (res.statusCode === 200) {

          console.log(res.data)// 服务器回包内容
          that.setData({
            eNum:data.examNum
          })
        }

      },
      fail: function (res) {

        wx.showToast({
          title: 'Wrong .. ',
          icon: 'none',
            })

      },
      complete: function (res) {
        that.setData({
          hasClick:false
        });
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})