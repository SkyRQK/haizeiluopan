// pages/leavetogether/leavetogether.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    thispagepersonalcarinfo: [],
    ifhavepersonalcarinfo: true,
    carriageResults:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that=this
    var personalopenid = wx.getStorageSync('personalopenid');
    wx.request({
      url: 'https://wechat.haizeix.com/getcarriagelist',
      header: {
        'Content-Type': 'application/json'
      },
      data: {
        "userId": personalopenid.openid
      },
      success: function (res) {

         if(res.data.length==0){
           wx.showModal({
             title: '提示',
             content:"请设置自己的车程",
             showCancel: false
           })
         }else{
           that.setData({
             thispagepersonalcarinfo: res.data[0],
             ifhavepersonalcarinfo: false,
           })
          //  console.log("ssssssssssssssssssssssssss")
          //  console.log(that.data.thispagepersonalcarinfo)
           wx.request({
             url: 'https://wechat.haizeix.com/getsamecarriagelist',
             header: {
               'Content-Type': 'application/json'
             },
             data: {
               "userId": personalopenid.openid
             },
             success: function (res) {
              //  console.log("到这了")
              //  console.log(res)
               if (res.data.success) {
                 that.setData({
                   carriageResults: res.data.carriageResults
                 })
               }
               else {

               }

             },
             fail: function (error) {

               console.log(error);
             }
           })


         }

      },
      fail: function (error) {
        console.log(error);
      }
    })
},

/**
 * 生命周期函数--监听页面隐藏
 */
onHide: function() {

},

/**
 * 生命周期函数--监听页面卸载
 */
onUnload: function() {

},

/**
 * 页面相关事件处理函数--监听用户下拉动作
 */
onPullDownRefresh: function() {

},

/**
 * 页面上拉触底事件的处理函数
 */
onReachBottom: function() {

},

/**
 * 用户点击右上角分享
 */
onShareAppMessage: function() {

}
})