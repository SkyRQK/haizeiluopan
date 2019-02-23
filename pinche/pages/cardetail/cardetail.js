
Page({

  /**
   * 页面的初始数据
   */
  data: {
    carperson:[]
  },

  onLoad: function (res) {
  var that=this
   // console.log(res)
    wx.request({
      url: 'https://wechat.haizeix.com/getteamMember',
      header: {
        'Content-Type': 'application/json'
      },
      data: {
        "groupId":res.groupid
      },
      success: function (res) {
        // console.log("进来了");
        // console.log(res)
        that.setData({
          carperson: res.data
        })
        // self.setData({
        //   mainCarsList: res.data
        // })
      },
      fail: function (error) {
        //调用服务端登录接口失败
        // that.showInfo('调用接口失败');
        // console.log(error);
      }
    })
  },
  // /**
  //  * 生命周期函数--监听页面加载
  //  */
  // onLoad: function (options) {

  // },

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