// pages/homepage/homepage.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
      imgUrls: [
      '/images/b1.jpg'
      // '/images/b2.jpg',
      // '/images/b3.jpg'
  ],
  indicatorDots: false,
  autoplay: false,
  interval: 3000,
  duration: 800,
  carNum: '无',
  ifReach: false,
  days: '0'

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
    var that=this
    var personalopenid = wx.getStorageSync('personalopenid');
      //请求距离回家时间的信息
    wx.request({
      url: 'https://wechat.haizeix.com/daysForCarriageList',
      method: 'GET',
      header: {
        'Content-Type': 'application/json'
      },
      data: {
        userId: personalopenid.openid,
      },
      success: function (res) {
        // console.log("hahahaha")
         console.log(res);
        //已经到家了
        if (res.data.days=='-1'){
          that.setData({
            days: res.data.days
          })
        }
        else if (res.data.days =='0'){
          that.setData({
            days: res.data.days
          })
        }else {
          //还没有到家
          that.setData({
            days: res.data.days,
            carNum: res.data.carNum
          })
        }
        
      },
      fail: function (res) {
        console.log(res)
      }
    })
  
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