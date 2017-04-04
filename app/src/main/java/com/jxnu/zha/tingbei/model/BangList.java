package com.jxnu.zha.tingbei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class BangList extends Entity{


    /**
     * obj : [{"context":"华语音乐排行榜1","id":"f39c8ce34e28edd8014e2d514bde0078","index_":"2","listMusic":[{"addTime":"2016-06-24 10:38:17","cooperation":"移动","id":"f39db5d355249e0201558045adbe00d0","initialId":"0fa13ce731261893a71ae5c3267cbab0","initialSingerId":"1212","isRecommend":1,"isTime":"2019-12-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2016/06/23/1606231601302515.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n14/music2/2016/06/2016%E5%B9%B406%E6%9C%8823%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E6%88%91%E5%A5%BD%E5%83%8F%E5%9C%A8%E5%93%AA%E8%A7%81%E8%BF%87%E4%BD%A0-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=9971ec99-3db5-41ce-abba-5b27c0c66307","musicPicPath":"http://218.200.230.40:18089/files/album/2016-06-23/1606231610532476.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2016-06-07/1606071102364397.jpg","name":"我好像在哪见过你","nameSpell":"whxznjgn","sell":2,"setRingTone":[{"addTime":"2016-06-24 10:38:17","cooperation":"移动","id":"f39db5d355249e0201558045adbe00d1","initialid":"0fa13ce731261893a71ae5c3267cbab0","isTime":"2019-12-31 00:00:00","name":"我好像在哪见过你","nameSpell":"whxznjgn","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n14/music2/2016/06/2016%E5%B9%B406%E6%9C%8823%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E6%88%91%E5%A5%BD%E5%83%8F%E5%9C%A8%E5%93%AA%E8%A7%81%E8%BF%87%E4%BD%A0-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=73bcb92a-2b5c-45e3-b370-b74e5ca2eee9","ringToneSell":2,"singerName":"薛之谦","singerNameSpell":"xzq"}],"singerName":"薛之谦","singerNameSpell":"xzq","valueFrom":"接口"},{"addTime":"2015-10-21 11:10:09","cooperation":"移动","id":"f39db5d350456f8f01508860133303cb","initialId":"c61ff9449c24baf17e19f53fb20da403","initialSingerId":"1002143102","isRecommend":1,"isTime":"2016-10-13 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2015/10/14/1510141723038844.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/dailyring03/vsftp/ywq/public/ringmaker01/dailyring03/2015/10/2015%E5%B9%B410%E6%9C%8814%E6%97%A509%E7%82%B951%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E4%B8%B0%E5%8D%8E%E7%A7%8B%E5%AE%9E1%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%8B%8B%E7%AB%A0-%E9%B9%BF%E6%99%97.mp3?channelid=08&msisdn=59ee8ec0-1524-439c-87d9-594037f93481","musicPathBak":"http://115.28.12.99/resource/musicbak/20151112/f39db5d350456f8f01508860133303cb.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2015-10-14/1510141019089590.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2014-12-03/141203113406911.jpg","name":"勋章","nameSpell":"xz","sell":2,"setRingTone":[{"addTime":"2015-10-21 11:10:09","cooperation":"移动","id":"f39db5d350456f8f01508860133303cc","initialid":"c61ff9449c24baf17e19f53fb20da403","isTime":"2016-10-13 00:00:00","name":"勋章","nameSpell":"xz","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/dailyring03/vsftp/ywq/public/ringmaker01/dailyring03/2015/10/2015%E5%B9%B410%E6%9C%8814%E6%97%A509%E7%82%B951%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E4%B8%B0%E5%8D%8E%E7%A7%8B%E5%AE%9E1%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%8B%8B%E7%AB%A0-%E9%B9%BF%E6%99%97.mp3?channelid=08&msisdn=3f18639a-8ee2-4da0-a55c-5e6a85979e46","ringToneSell":2,"singerName":"鹿晗","singerNameSpell":"lh"}],"singerName":"鹿晗","singerNameSpell":"lh","valueFrom":"接口"},{"addTime":"2016-01-12 14:58:17","cooperation":"移动","id":"f39db5d351f6b2d8015234a0c3f31ea7","initialId":"3779047a320e77bfd1ce6dae00141153","initialSingerId":"529","isRecommend":1,"isTime":"2017-12-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2015/12/31/1512310901039612.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/dailyring03/vsftp/ywq/public/ringmaker01/dailyring03/2015/12/2015%E5%B9%B412%E6%9C%8830%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E7%9B%B8%E4%BF%A1%E9%9F%B3%E4%B9%90501%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%8B%87%E6%95%A2-%E4%BA%94%E6%9C%88%E5%A4%A9.mp3?channelid=08&msisdn=a6a1802f-54a5-40ac-8f4c-81a7737e8b61","musicPathBak":"http://115.28.12.99/resource/musicbak/20160113/f39db5d351f6b2d8015234a0c3f31ea7.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2015-12-30/1512301429264796.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2014-11-12/1411121441066953.jpg","name":"勇敢","nameSpell":"yg","sell":2,"setRingTone":[{"addTime":"2016-01-12 14:58:17","cooperation":"移动","id":"f39db5d351f6b2d8015234a0c3f31ea8","initialid":"3779047a320e77bfd1ce6dae00141153","isTime":"2017-12-31 00:00:00","name":"勇敢","nameSpell":"yg","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/dailyring03/vsftp/ywq/public/ringmaker01/dailyring03/2015/12/2015%E5%B9%B412%E6%9C%8830%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E7%9B%B8%E4%BF%A1%E9%9F%B3%E4%B9%90501%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%8B%87%E6%95%A2-%E4%BA%94%E6%9C%88%E5%A4%A9.mp3?channelid=08&msisdn=6b229396-3b59-4b3e-a606-fb195893e9b8","ringToneSell":2,"singerName":"五月天","singerNameSpell":"wyt"}],"singerName":"五月天","singerNameSpell":"wyt","valueFrom":"接口"},{"addTime":"2016-06-29 16:42:11","cooperation":"移动","id":"f39db5d3559afa2c01559b52a213001f","initialId":"0fa13ce73126189313522193a88218e0","initialSingerId":"1212","isRecommend":1,"isTime":"2019-12-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2016/06/09/1606071018014392.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n10/music2/2016/06/2016%E5%B9%B406%E6%9C%8806%E6%97%A517%E7%82%B902%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%88%9A%E5%88%9A%E5%A5%BD-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=684dcf7a-55b2-4379-93e6-768175474b36","musicPicPath":"http://218.200.230.40:18089/files/album/2016-06-07/1606070923476957.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2016-06-07/1606071102364397.jpg","name":"刚刚好","nameSpell":"ggh","sell":2,"setRingTone":[{"addTime":"2016-06-29 16:42:11","cooperation":"移动","id":"f39db5d3559afa2c01559b52a2130020","initialid":"0fa13ce73126189313522193a88218e0","isTime":"2019-12-31 00:00:00","name":"刚刚好","nameSpell":"ggh","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n10/music2/2016/06/2016%E5%B9%B406%E6%9C%8806%E6%97%A517%E7%82%B902%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%88%9A%E5%88%9A%E5%A5%BD-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=573cd8fb-9ba7-47b0-b93e-4ce3129af2de","ringToneSell":2,"singerName":"薛之谦","singerNameSpell":"xzq"}],"singerName":"薛之谦","singerNameSpell":"xzq","valueFrom":"接口"},{"addTime":"2016-07-08 11:19:06","cooperation":"移动","id":"f39db5d355ba2db20155c884132c000b","initialId":"56292f0c73362c247df4a29eeebe2e3c","initialSingerId":"1002043527","isRecommend":1,"isTime":"2018-05-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2016/07/06/1605311059258365.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n10/2016/05/2016%E5%B9%B405%E6%9C%8830%E6%97%A517%E7%82%B914%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%A2%A6%E5%93%8D%E5%BC%BA%E9%9F%B3501%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%A4%A7%E9%B1%BC%28%E5%A4%A7%E9%B1%BC%E6%B5%B7%E6%A3%A0%E5%8D%B0%E8%B1%A1%E6%9B%B2%29-%E5%91%A8%E6%B7%B1.mp3?channelid=08&msisdn=544856e0-d971-4cec-a22f-bac728c1ef75","musicPicPath":"http://218.200.230.40:18089/files/album/2016-05-30/1605301721385299.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2015-06-01/1506011641168409.jpg","name":"大鱼(大鱼海棠印象曲)","nameSpell":"dydyhtyxq","sell":2,"setRingTone":[{"addTime":"2016-07-08 11:19:06","cooperation":"移动","id":"f39db5d355ba2db20155c884132c000c","initialid":"56292f0c73362c247df4a29eeebe2e3c","isTime":"2018-05-31 00:00:00","name":"大鱼(大鱼海棠印象曲)","nameSpell":"dydyhtyxq","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n10/2016/05/2016%E5%B9%B405%E6%9C%8830%E6%97%A517%E7%82%B914%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%A2%A6%E5%93%8D%E5%BC%BA%E9%9F%B3501%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%A4%A7%E9%B1%BC%28%E5%A4%A7%E9%B1%BC%E6%B5%B7%E6%A3%A0%E5%8D%B0%E8%B1%A1%E6%9B%B2%29-%E5%91%A8%E6%B7%B1.mp3?channelid=08&msisdn=f61fc71f-c8f2-43cd-b6ed-4f7bf3ff3d9d","ringToneSell":2,"singerName":"周深","singerNameSpell":"zs"}],"singerName":"周深","singerNameSpell":"zs","valueFrom":"接口"},{"addTime":"2015-07-15 16:07:13","cooperation":"移动","id":"f39c8ce34e86bac3014e90c0d5d9168d","initialId":"32e574b02d2445ac108f546e80189903","initialSingerId":"266","isRecommend":1,"isTime":"2016-10-30 00:00:00","lrcPath":"http://open.migu.cn:8100/product/music/3/000021/2014/10/10/751a8b06ce2d93b80d284ae1169d4e19_3.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n3/Product/2010/12/2010%E5%B9%B412%E6%9C%889%E6%97%A5%E9%A2%84%E5%AE%A1%E6%B5%B7%E8%9D%B6%E6%9E%97%E4%BF%8A%E6%9D%B011%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%A5%B9%E8%AF%B4-%E6%9E%97%E4%BF%8A%E6%9D%B0.mp3?channelid=08&msisdn=a3dc2e8a-7d92-4e59-b60a-58c4d1bdb917","musicPathBak":"http://115.28.12.99/resource/musicbak/20151111/f39c8ce34e86bac3014e90c0d5d9168d.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2014-03-11/1403112013244225.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2014-12-02/1412021445597225.jpg","name":"她说","nameSpell":"ts","sell":2,"setRingTone":[{"addTime":"2015-07-15 16:07:13","cooperation":"移动","id":"f39c8ce34e86bac3014e90c0d5d9168e","initialid":"32e574b02d2445ac108f546e80189903","isTime":"2016-10-30 00:00:00","name":"她说","nameSpell":"ts","ringTonePath":"http://tyst.migu.cn/public/600902-2008430/tone/2014/11/28/2014112818/update/%E5%A5%B9%E8%AF%B4-%E6%9E%97%E4%BF%8A%E6%9D%B0+/000004/%E5%A5%B9%E8%AF%B4-%E6%9E%97%E4%BF%8A%E6%9D%B0+.mp3?channelid=08&msisdn=bcc64e48-eee6-4e38-af0d-0259e37300fd","ringToneSell":2,"singerName":"林俊杰","singerNameSpell":"ljj"}],"singerName":"林俊杰","singerNameSpell":"ljj","valueFrom":"接口"},{"addTime":"2016-06-29 16:57:34","cooperation":"移动","id":"f39db5d3559afa2c01559b60b7aa002e","initialId":"0fa13ce731261893b9d493bd8a20d64d","initialSingerId":"18196","isRecommend":1,"isTime":"2018-12-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2016/06/27/1605200954208116.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n9/2016/05/2016%E5%B9%B405%E6%9C%8819%E6%97%A517%E7%82%B917%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E6%9C%80%E4%BD%B3%E6%AD%8C%E6%89%8B-%E8%AE%B8%E5%B5%A9.mp3?channelid=08&msisdn=923db720-95dc-4e7d-8583-93e7e72363d1","musicPicPath":"http://218.200.230.40:18089/files/album/2016-06-24/1606241731424968.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2015-08-14/1508141732022773.jpg","name":"最佳歌手","nameSpell":"zjgs","sell":2,"setRingTone":[{"addTime":"2016-06-29 16:57:34","cooperation":"移动","id":"f39db5d3559afa2c01559b60b7aa002f","initialid":"0fa13ce731261893b9d493bd8a20d64d","isTime":"2018-12-31 00:00:00","name":"最佳歌手","nameSpell":"zjgs","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n9/2016/05/2016%E5%B9%B405%E6%9C%8819%E6%97%A517%E7%82%B917%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E6%9C%80%E4%BD%B3%E6%AD%8C%E6%89%8B-%E8%AE%B8%E5%B5%A9.mp3?channelid=08&msisdn=b919eb57-6763-4a8e-8e96-fe508da4a54f","ringToneSell":2,"singerName":"许嵩","singerNameSpell":"xs"}],"singerName":"许嵩","singerNameSpell":"xs","valueFrom":"接口"},{"addTime":"2016-06-29 16:12:58","cooperation":"自有","id":"f39c8ce355917c0b01559b33b903013e","initialId":"bcf84eec13c645cea9b16596c243bb45","initialSingerId":"480ba4c5e65341ec88d36f1ceeb082b9","isRecommend":0,"isTime":"2016-06-29 00:00:00","lrcPath":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7hste3nb5a1f4k1kieauj3b.lrc","musicPath":"http://7xsr9t.com2.z0.glb.qiniucdn.com/o_1amdj77qq2ls8559uq1h56d5o2n.mp3","musicPathBak":"http://115.28.12.99/resource/musicbak/20160630/f39c8ce355917c0b01559b33b903013e.mp3","musicPicPath":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7evajgoflj1u351c8i133v36.jpg","musicPicPathSmall":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7evajgoflj1u351c8i133v36.jpg","musicSingerPicPath":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7c0c1f1i15241hrbo9h19io31.jpg","musicSingerPicPathSmall":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7c0c1f1i15241hrbo9h19io31.jpg","musicType":6,"name":"啷个哩个啷","nameSpell":"lglgl","sell":2,"setRingTone":[],"singerName":"鹏泊","singerNameSpell":"pb","valueFrom":"界面"},{"addTime":"2015-09-24 17:19:12","cooperation":"移动","id":"f39db5d34ffea075014ffea6416e0011","initialId":"49cacac4b6cced46cb6607afd1a15615","initialSingerId":"7988","isRecommend":1,"isTime":"2017-07-01 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2015/08/08/1508071226492969.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/dailyring02/2015/08/2015%E5%B9%B408%E6%9C%8806%E6%97%A514%E7%82%B933%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E5%8D%8E%E8%8D%A3%E6%96%87%E5%8C%96%E9%A2%84%E7%95%991%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%B0%8F%E5%B9%B8%E8%BF%90%28%E7%94%B5%E5%BD%B1%E6%88%91%E7%9A%84%E5%B0%91%E5%A5%B3%E6%97%B6%E4%BB%A3%E4%B8%BB%E9%A2%98%E6%9B%B2%29-%E7%94%B0%E9%A6%A5%E7%94%84.mp3?channelid=08&msisdn=32a57ece-7490-4c0c-9e71-de15ade15cb1","musicPathBak":"http://115.28.12.99/resource/musicbak/20151111/f39db5d34ffea075014ffea6416e0011.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2015-07-14/1507141018026327.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2015-09-11/1509111737155834.jpg","name":"小幸运(电影我的少女时代主题曲)","nameSpell":"xxydywdsnsdztq","sell":2,"setRingTone":[{"addTime":"2015-09-24 17:19:12","cooperation":"移动","id":"f39db5d34ffea075014ffea6416e0012","initialid":"49cacac4b6cced46cb6607afd1a15615","isTime":"2017-07-01 00:00:00","name":"小幸运(电影我的少女时代主题曲)","nameSpell":"xxydywdsnsdztq","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/dailyring02/2015/08/2015%E5%B9%B408%E6%9C%8806%E6%97%A514%E7%82%B933%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E5%8D%8E%E8%8D%A3%E6%96%87%E5%8C%96%E9%A2%84%E7%95%991%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%B0%8F%E5%B9%B8%E8%BF%90%28%E7%94%B5%E5%BD%B1%E6%88%91%E7%9A%84%E5%B0%91%E5%A5%B3%E6%97%B6%E4%BB%A3%E4%B8%BB%E9%A2%98%E6%9B%B2%29-%E7%94%B0%E9%A6%A5%E7%94%84.mp3?channelid=08&msisdn=6ea866a1-2c9b-47fb-93b1-17e1b76e4d62","ringToneSell":2,"singerName":"田馥甄","singerNameSpell":"tfz"}],"singerName":"田馥甄","singerNameSpell":"tfz","valueFrom":"接口"},{"addTime":"2015-10-21 11:32:37","cooperation":"移动","id":"f39db5d350456f8f01508874a698064d","initialId":"6f82086f93ac565c1cd826ae7842d0a6","initialSingerId":"1002060070","isRecommend":1,"isTime":"2017-09-01 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2015/09/22/1509022240549986.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/dailyring02/2015/09/2015%E5%B9%B409%E6%9C%8801%E6%97%A514%E7%82%B943%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%A2%A6%E5%93%8D%E5%BC%BA%E9%9F%B31%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E6%BC%82%E6%B4%8B%E8%BF%87%E6%B5%B7%E6%9D%A5%E7%9C%8B%E4%BD%A0%28%E4%B8%AD%E5%9B%BD%E5%A5%BD%E5%A3%B0%E9%9F%B3%E7%AC%AC%E4%B8%89%E5%AD%A3%29-%E5%88%98%E6%98%8E%E6%B9%98.mp3?channelid=08&msisdn=369dd4c8-9f17-41cd-bb4e-92b7d25f935e","musicPathBak":"http://115.28.12.99/resource/musicbak/20151112/f39db5d350456f8f01508874a698064d.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2015-09-17/150917105547360.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2015-09-06/1509061014034483.jpg","name":"漂洋过海来看你(中国好声音第三季)","nameSpell":"pyghlknzghsydsj","sell":2,"setRingTone":[{"addTime":"2015-10-21 11:32:37","cooperation":"移动","id":"f39db5d350456f8f01508874a698064f","initialid":"6f82086f93ac565c1cd826ae7842d0a6","isTime":"2017-09-01 00:00:00","name":"漂洋过海来看你(中国好声音第三季)","nameSpell":"pyghlknzghsydsj","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/dailyring02/2015/09/2015%E5%B9%B409%E6%9C%8801%E6%97%A514%E7%82%B943%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%A2%A6%E5%93%8D%E5%BC%BA%E9%9F%B31%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E6%BC%82%E6%B4%8B%E8%BF%87%E6%B5%B7%E6%9D%A5%E7%9C%8B%E4%BD%A0%28%E4%B8%AD%E5%9B%BD%E5%A5%BD%E5%A3%B0%E9%9F%B3%E7%AC%AC%E4%B8%89%E5%AD%A3%29-%E5%88%98%E6%98%8E%E6%B9%98.mp3?channelid=08&msisdn=9bdde41b-340e-4550-bd2c-c8ba972013f3","ringToneSell":2,"singerName":"刘明湘","singerNameSpell":"lmx"}],"singerName":"刘明湘","singerNameSpell":"lmx","valueFrom":"接口"}],"name":"华语音乐排行榜1","openUrl":"","picPath":"http://www.nnshow.cn/resource/musiclist/20150626/3a173e8731134e8e8a6691529012ff6c.png","picPathSmall":"http://www.nnshow.cn/resource/musiclist/20150626/3a173e8731134e8e8a6691529012ff6c-s.png","title":"华语音乐排行榜1"}]
     */
    private int bangListType;
    private List<ObjEntity> obj;

    public int getBangListType() {
        return bangListType;
    }

    public void setBangListType(int bangListType) {
        this.bangListType = bangListType;
    }

    public void setObj(List<ObjEntity> obj) {
        this.obj = obj;
    }

    public List<ObjEntity> getObj() {
        return obj;
    }

    public static class ObjEntity implements Serializable{
        /**
         * context : 华语音乐排行榜1
         * id : f39c8ce34e28edd8014e2d514bde0078
         * index_ : 2
         * listMusic : [{"addTime":"2016-06-24 10:38:17","cooperation":"移动","id":"f39db5d355249e0201558045adbe00d0","initialId":"0fa13ce731261893a71ae5c3267cbab0","initialSingerId":"1212","isRecommend":1,"isTime":"2019-12-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2016/06/23/1606231601302515.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n14/music2/2016/06/2016%E5%B9%B406%E6%9C%8823%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E6%88%91%E5%A5%BD%E5%83%8F%E5%9C%A8%E5%93%AA%E8%A7%81%E8%BF%87%E4%BD%A0-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=9971ec99-3db5-41ce-abba-5b27c0c66307","musicPicPath":"http://218.200.230.40:18089/files/album/2016-06-23/1606231610532476.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2016-06-07/1606071102364397.jpg","name":"我好像在哪见过你","nameSpell":"whxznjgn","sell":2,"setRingTone":[{"addTime":"2016-06-24 10:38:17","cooperation":"移动","id":"f39db5d355249e0201558045adbe00d1","initialid":"0fa13ce731261893a71ae5c3267cbab0","isTime":"2019-12-31 00:00:00","name":"我好像在哪见过你","nameSpell":"whxznjgn","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n14/music2/2016/06/2016%E5%B9%B406%E6%9C%8823%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E6%88%91%E5%A5%BD%E5%83%8F%E5%9C%A8%E5%93%AA%E8%A7%81%E8%BF%87%E4%BD%A0-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=73bcb92a-2b5c-45e3-b370-b74e5ca2eee9","ringToneSell":2,"singerName":"薛之谦","singerNameSpell":"xzq"}],"singerName":"薛之谦","singerNameSpell":"xzq","valueFrom":"接口"},{"addTime":"2015-10-21 11:10:09","cooperation":"移动","id":"f39db5d350456f8f01508860133303cb","initialId":"c61ff9449c24baf17e19f53fb20da403","initialSingerId":"1002143102","isRecommend":1,"isTime":"2016-10-13 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2015/10/14/1510141723038844.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/dailyring03/vsftp/ywq/public/ringmaker01/dailyring03/2015/10/2015%E5%B9%B410%E6%9C%8814%E6%97%A509%E7%82%B951%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E4%B8%B0%E5%8D%8E%E7%A7%8B%E5%AE%9E1%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%8B%8B%E7%AB%A0-%E9%B9%BF%E6%99%97.mp3?channelid=08&msisdn=59ee8ec0-1524-439c-87d9-594037f93481","musicPathBak":"http://115.28.12.99/resource/musicbak/20151112/f39db5d350456f8f01508860133303cb.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2015-10-14/1510141019089590.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2014-12-03/141203113406911.jpg","name":"勋章","nameSpell":"xz","sell":2,"setRingTone":[{"addTime":"2015-10-21 11:10:09","cooperation":"移动","id":"f39db5d350456f8f01508860133303cc","initialid":"c61ff9449c24baf17e19f53fb20da403","isTime":"2016-10-13 00:00:00","name":"勋章","nameSpell":"xz","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/dailyring03/vsftp/ywq/public/ringmaker01/dailyring03/2015/10/2015%E5%B9%B410%E6%9C%8814%E6%97%A509%E7%82%B951%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E4%B8%B0%E5%8D%8E%E7%A7%8B%E5%AE%9E1%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%8B%8B%E7%AB%A0-%E9%B9%BF%E6%99%97.mp3?channelid=08&msisdn=3f18639a-8ee2-4da0-a55c-5e6a85979e46","ringToneSell":2,"singerName":"鹿晗","singerNameSpell":"lh"}],"singerName":"鹿晗","singerNameSpell":"lh","valueFrom":"接口"},{"addTime":"2016-01-12 14:58:17","cooperation":"移动","id":"f39db5d351f6b2d8015234a0c3f31ea7","initialId":"3779047a320e77bfd1ce6dae00141153","initialSingerId":"529","isRecommend":1,"isTime":"2017-12-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2015/12/31/1512310901039612.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/dailyring03/vsftp/ywq/public/ringmaker01/dailyring03/2015/12/2015%E5%B9%B412%E6%9C%8830%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E7%9B%B8%E4%BF%A1%E9%9F%B3%E4%B9%90501%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%8B%87%E6%95%A2-%E4%BA%94%E6%9C%88%E5%A4%A9.mp3?channelid=08&msisdn=a6a1802f-54a5-40ac-8f4c-81a7737e8b61","musicPathBak":"http://115.28.12.99/resource/musicbak/20160113/f39db5d351f6b2d8015234a0c3f31ea7.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2015-12-30/1512301429264796.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2014-11-12/1411121441066953.jpg","name":"勇敢","nameSpell":"yg","sell":2,"setRingTone":[{"addTime":"2016-01-12 14:58:17","cooperation":"移动","id":"f39db5d351f6b2d8015234a0c3f31ea8","initialid":"3779047a320e77bfd1ce6dae00141153","isTime":"2017-12-31 00:00:00","name":"勇敢","nameSpell":"yg","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/dailyring03/vsftp/ywq/public/ringmaker01/dailyring03/2015/12/2015%E5%B9%B412%E6%9C%8830%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E7%9B%B8%E4%BF%A1%E9%9F%B3%E4%B9%90501%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%8B%87%E6%95%A2-%E4%BA%94%E6%9C%88%E5%A4%A9.mp3?channelid=08&msisdn=6b229396-3b59-4b3e-a606-fb195893e9b8","ringToneSell":2,"singerName":"五月天","singerNameSpell":"wyt"}],"singerName":"五月天","singerNameSpell":"wyt","valueFrom":"接口"},{"addTime":"2016-06-29 16:42:11","cooperation":"移动","id":"f39db5d3559afa2c01559b52a213001f","initialId":"0fa13ce73126189313522193a88218e0","initialSingerId":"1212","isRecommend":1,"isTime":"2019-12-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2016/06/09/1606071018014392.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n10/music2/2016/06/2016%E5%B9%B406%E6%9C%8806%E6%97%A517%E7%82%B902%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%88%9A%E5%88%9A%E5%A5%BD-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=684dcf7a-55b2-4379-93e6-768175474b36","musicPicPath":"http://218.200.230.40:18089/files/album/2016-06-07/1606070923476957.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2016-06-07/1606071102364397.jpg","name":"刚刚好","nameSpell":"ggh","sell":2,"setRingTone":[{"addTime":"2016-06-29 16:42:11","cooperation":"移动","id":"f39db5d3559afa2c01559b52a2130020","initialid":"0fa13ce73126189313522193a88218e0","isTime":"2019-12-31 00:00:00","name":"刚刚好","nameSpell":"ggh","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n10/music2/2016/06/2016%E5%B9%B406%E6%9C%8806%E6%97%A517%E7%82%B902%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%88%9A%E5%88%9A%E5%A5%BD-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=573cd8fb-9ba7-47b0-b93e-4ce3129af2de","ringToneSell":2,"singerName":"薛之谦","singerNameSpell":"xzq"}],"singerName":"薛之谦","singerNameSpell":"xzq","valueFrom":"接口"},{"addTime":"2016-07-08 11:19:06","cooperation":"移动","id":"f39db5d355ba2db20155c884132c000b","initialId":"56292f0c73362c247df4a29eeebe2e3c","initialSingerId":"1002043527","isRecommend":1,"isTime":"2018-05-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2016/07/06/1605311059258365.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n10/2016/05/2016%E5%B9%B405%E6%9C%8830%E6%97%A517%E7%82%B914%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%A2%A6%E5%93%8D%E5%BC%BA%E9%9F%B3501%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%A4%A7%E9%B1%BC%28%E5%A4%A7%E9%B1%BC%E6%B5%B7%E6%A3%A0%E5%8D%B0%E8%B1%A1%E6%9B%B2%29-%E5%91%A8%E6%B7%B1.mp3?channelid=08&msisdn=544856e0-d971-4cec-a22f-bac728c1ef75","musicPicPath":"http://218.200.230.40:18089/files/album/2016-05-30/1605301721385299.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2015-06-01/1506011641168409.jpg","name":"大鱼(大鱼海棠印象曲)","nameSpell":"dydyhtyxq","sell":2,"setRingTone":[{"addTime":"2016-07-08 11:19:06","cooperation":"移动","id":"f39db5d355ba2db20155c884132c000c","initialid":"56292f0c73362c247df4a29eeebe2e3c","isTime":"2018-05-31 00:00:00","name":"大鱼(大鱼海棠印象曲)","nameSpell":"dydyhtyxq","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n10/2016/05/2016%E5%B9%B405%E6%9C%8830%E6%97%A517%E7%82%B914%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%A2%A6%E5%93%8D%E5%BC%BA%E9%9F%B3501%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%A4%A7%E9%B1%BC%28%E5%A4%A7%E9%B1%BC%E6%B5%B7%E6%A3%A0%E5%8D%B0%E8%B1%A1%E6%9B%B2%29-%E5%91%A8%E6%B7%B1.mp3?channelid=08&msisdn=f61fc71f-c8f2-43cd-b6ed-4f7bf3ff3d9d","ringToneSell":2,"singerName":"周深","singerNameSpell":"zs"}],"singerName":"周深","singerNameSpell":"zs","valueFrom":"接口"},{"addTime":"2015-07-15 16:07:13","cooperation":"移动","id":"f39c8ce34e86bac3014e90c0d5d9168d","initialId":"32e574b02d2445ac108f546e80189903","initialSingerId":"266","isRecommend":1,"isTime":"2016-10-30 00:00:00","lrcPath":"http://open.migu.cn:8100/product/music/3/000021/2014/10/10/751a8b06ce2d93b80d284ae1169d4e19_3.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n3/Product/2010/12/2010%E5%B9%B412%E6%9C%889%E6%97%A5%E9%A2%84%E5%AE%A1%E6%B5%B7%E8%9D%B6%E6%9E%97%E4%BF%8A%E6%9D%B011%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%A5%B9%E8%AF%B4-%E6%9E%97%E4%BF%8A%E6%9D%B0.mp3?channelid=08&msisdn=a3dc2e8a-7d92-4e59-b60a-58c4d1bdb917","musicPathBak":"http://115.28.12.99/resource/musicbak/20151111/f39c8ce34e86bac3014e90c0d5d9168d.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2014-03-11/1403112013244225.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2014-12-02/1412021445597225.jpg","name":"她说","nameSpell":"ts","sell":2,"setRingTone":[{"addTime":"2015-07-15 16:07:13","cooperation":"移动","id":"f39c8ce34e86bac3014e90c0d5d9168e","initialid":"32e574b02d2445ac108f546e80189903","isTime":"2016-10-30 00:00:00","name":"她说","nameSpell":"ts","ringTonePath":"http://tyst.migu.cn/public/600902-2008430/tone/2014/11/28/2014112818/update/%E5%A5%B9%E8%AF%B4-%E6%9E%97%E4%BF%8A%E6%9D%B0+/000004/%E5%A5%B9%E8%AF%B4-%E6%9E%97%E4%BF%8A%E6%9D%B0+.mp3?channelid=08&msisdn=bcc64e48-eee6-4e38-af0d-0259e37300fd","ringToneSell":2,"singerName":"林俊杰","singerNameSpell":"ljj"}],"singerName":"林俊杰","singerNameSpell":"ljj","valueFrom":"接口"},{"addTime":"2016-06-29 16:57:34","cooperation":"移动","id":"f39db5d3559afa2c01559b60b7aa002e","initialId":"0fa13ce731261893b9d493bd8a20d64d","initialSingerId":"18196","isRecommend":1,"isTime":"2018-12-31 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2016/06/27/1605200954208116.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n9/2016/05/2016%E5%B9%B405%E6%9C%8819%E6%97%A517%E7%82%B917%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E6%9C%80%E4%BD%B3%E6%AD%8C%E6%89%8B-%E8%AE%B8%E5%B5%A9.mp3?channelid=08&msisdn=923db720-95dc-4e7d-8583-93e7e72363d1","musicPicPath":"http://218.200.230.40:18089/files/album/2016-06-24/1606241731424968.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2015-08-14/1508141732022773.jpg","name":"最佳歌手","nameSpell":"zjgs","sell":2,"setRingTone":[{"addTime":"2016-06-29 16:57:34","cooperation":"移动","id":"f39db5d3559afa2c01559b60b7aa002f","initialid":"0fa13ce731261893b9d493bd8a20d64d","isTime":"2018-12-31 00:00:00","name":"最佳歌手","nameSpell":"zjgs","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n9/2016/05/2016%E5%B9%B405%E6%9C%8819%E6%97%A517%E7%82%B917%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E6%9C%80%E4%BD%B3%E6%AD%8C%E6%89%8B-%E8%AE%B8%E5%B5%A9.mp3?channelid=08&msisdn=b919eb57-6763-4a8e-8e96-fe508da4a54f","ringToneSell":2,"singerName":"许嵩","singerNameSpell":"xs"}],"singerName":"许嵩","singerNameSpell":"xs","valueFrom":"接口"},{"addTime":"2016-06-29 16:12:58","cooperation":"自有","id":"f39c8ce355917c0b01559b33b903013e","initialId":"bcf84eec13c645cea9b16596c243bb45","initialSingerId":"480ba4c5e65341ec88d36f1ceeb082b9","isRecommend":0,"isTime":"2016-06-29 00:00:00","lrcPath":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7hste3nb5a1f4k1kieauj3b.lrc","musicPath":"http://7xsr9t.com2.z0.glb.qiniucdn.com/o_1amdj77qq2ls8559uq1h56d5o2n.mp3","musicPathBak":"http://115.28.12.99/resource/musicbak/20160630/f39c8ce355917c0b01559b33b903013e.mp3","musicPicPath":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7evajgoflj1u351c8i133v36.jpg","musicPicPathSmall":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7evajgoflj1u351c8i133v36.jpg","musicSingerPicPath":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7c0c1f1i15241hrbo9h19io31.jpg","musicSingerPicPathSmall":"http://7xsr9w.com2.z0.glb.qiniucdn.com/o_1amdj7c0c1f1i15241hrbo9h19io31.jpg","musicType":6,"name":"啷个哩个啷","nameSpell":"lglgl","sell":2,"setRingTone":[],"singerName":"鹏泊","singerNameSpell":"pb","valueFrom":"界面"},{"addTime":"2015-09-24 17:19:12","cooperation":"移动","id":"f39db5d34ffea075014ffea6416e0011","initialId":"49cacac4b6cced46cb6607afd1a15615","initialSingerId":"7988","isRecommend":1,"isTime":"2017-07-01 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2015/08/08/1508071226492969.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/dailyring02/2015/08/2015%E5%B9%B408%E6%9C%8806%E6%97%A514%E7%82%B933%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E5%8D%8E%E8%8D%A3%E6%96%87%E5%8C%96%E9%A2%84%E7%95%991%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E5%B0%8F%E5%B9%B8%E8%BF%90%28%E7%94%B5%E5%BD%B1%E6%88%91%E7%9A%84%E5%B0%91%E5%A5%B3%E6%97%B6%E4%BB%A3%E4%B8%BB%E9%A2%98%E6%9B%B2%29-%E7%94%B0%E9%A6%A5%E7%94%84.mp3?channelid=08&msisdn=32a57ece-7490-4c0c-9e71-de15ade15cb1","musicPathBak":"http://115.28.12.99/resource/musicbak/20151111/f39db5d34ffea075014ffea6416e0011.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2015-07-14/1507141018026327.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2015-09-11/1509111737155834.jpg","name":"小幸运(电影我的少女时代主题曲)","nameSpell":"xxydywdsnsdztq","sell":2,"setRingTone":[{"addTime":"2015-09-24 17:19:12","cooperation":"移动","id":"f39db5d34ffea075014ffea6416e0012","initialid":"49cacac4b6cced46cb6607afd1a15615","isTime":"2017-07-01 00:00:00","name":"小幸运(电影我的少女时代主题曲)","nameSpell":"xxydywdsnsdztq","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/dailyring02/2015/08/2015%E5%B9%B408%E6%9C%8806%E6%97%A514%E7%82%B933%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E5%8D%8E%E8%8D%A3%E6%96%87%E5%8C%96%E9%A2%84%E7%95%991%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E5%B0%8F%E5%B9%B8%E8%BF%90%28%E7%94%B5%E5%BD%B1%E6%88%91%E7%9A%84%E5%B0%91%E5%A5%B3%E6%97%B6%E4%BB%A3%E4%B8%BB%E9%A2%98%E6%9B%B2%29-%E7%94%B0%E9%A6%A5%E7%94%84.mp3?channelid=08&msisdn=6ea866a1-2c9b-47fb-93b1-17e1b76e4d62","ringToneSell":2,"singerName":"田馥甄","singerNameSpell":"tfz"}],"singerName":"田馥甄","singerNameSpell":"tfz","valueFrom":"接口"},{"addTime":"2015-10-21 11:32:37","cooperation":"移动","id":"f39db5d350456f8f01508874a698064d","initialId":"6f82086f93ac565c1cd826ae7842d0a6","initialSingerId":"1002060070","isRecommend":1,"isTime":"2017-09-01 00:00:00","lrcPath":"http://open.migu.cn:8100/material/lyric/2015/09/22/1509022240549986.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/dailyring02/2015/09/2015%E5%B9%B409%E6%9C%8801%E6%97%A514%E7%82%B943%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%A2%A6%E5%93%8D%E5%BC%BA%E9%9F%B31%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E6%BC%82%E6%B4%8B%E8%BF%87%E6%B5%B7%E6%9D%A5%E7%9C%8B%E4%BD%A0%28%E4%B8%AD%E5%9B%BD%E5%A5%BD%E5%A3%B0%E9%9F%B3%E7%AC%AC%E4%B8%89%E5%AD%A3%29-%E5%88%98%E6%98%8E%E6%B9%98.mp3?channelid=08&msisdn=369dd4c8-9f17-41cd-bb4e-92b7d25f935e","musicPathBak":"http://115.28.12.99/resource/musicbak/20151112/f39db5d350456f8f01508874a698064d.mp3","musicPicPath":"http://218.200.230.40:18089/files/album/2015-09-17/150917105547360.jpg","musicSingerPicPath":"http://218.200.230.40:18089/files/artist/2015-09-06/1509061014034483.jpg","name":"漂洋过海来看你(中国好声音第三季)","nameSpell":"pyghlknzghsydsj","sell":2,"setRingTone":[{"addTime":"2015-10-21 11:32:37","cooperation":"移动","id":"f39db5d350456f8f01508874a698064f","initialid":"6f82086f93ac565c1cd826ae7842d0a6","isTime":"2017-09-01 00:00:00","name":"漂洋过海来看你(中国好声音第三季)","nameSpell":"pyghlknzghsydsj","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/dailyring02/2015/09/2015%E5%B9%B409%E6%9C%8801%E6%97%A514%E7%82%B943%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%A2%A6%E5%93%8D%E5%BC%BA%E9%9F%B31%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E6%BC%82%E6%B4%8B%E8%BF%87%E6%B5%B7%E6%9D%A5%E7%9C%8B%E4%BD%A0%28%E4%B8%AD%E5%9B%BD%E5%A5%BD%E5%A3%B0%E9%9F%B3%E7%AC%AC%E4%B8%89%E5%AD%A3%29-%E5%88%98%E6%98%8E%E6%B9%98.mp3?channelid=08&msisdn=9bdde41b-340e-4550-bd2c-c8ba972013f3","ringToneSell":2,"singerName":"刘明湘","singerNameSpell":"lmx"}],"singerName":"刘明湘","singerNameSpell":"lmx","valueFrom":"接口"}]
         * name : 华语音乐排行榜1
         * openUrl :
         * picPath : http://www.nnshow.cn/resource/musiclist/20150626/3a173e8731134e8e8a6691529012ff6c.png
         * picPathSmall : http://www.nnshow.cn/resource/musiclist/20150626/3a173e8731134e8e8a6691529012ff6c-s.png
         * title : 华语音乐排行榜1
         */

        private String context;
        private String id;
        private String index_;
        private String name;
        private String openUrl;
        private String picPath;
        private String picPathSmall;
        private String title;
        private List<ListMusicEntity> listMusic;

        public void setContext(String context) {
            this.context = context;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIndex_(String index_) {
            this.index_ = index_;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOpenUrl(String openUrl) {
            this.openUrl = openUrl;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public void setPicPathSmall(String picPathSmall) {
            this.picPathSmall = picPathSmall;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setListMusic(List<ListMusicEntity> listMusic) {
            this.listMusic = listMusic;
        }

        public String getContext() {
            return context;
        }

        public String getId() {
            return id;
        }

        public String getIndex_() {
            return index_;
        }

        public String getName() {
            return name;
        }

        public String getOpenUrl() {
            return openUrl;
        }

        public String getPicPath() {
            return picPath;
        }

        public String getPicPathSmall() {
            return picPathSmall;
        }

        public String getTitle() {
            return title;
        }

        public List<ListMusicEntity> getListMusic() {
            return listMusic;
        }

        public static class ListMusicEntity implements Serializable{
            /**
             * addTime : 2016-06-24 10:38:17
             * cooperation : 移动
             * id : f39db5d355249e0201558045adbe00d0
             * initialId : 0fa13ce731261893a71ae5c3267cbab0
             * initialSingerId : 1212
             * isRecommend : 1
             * isTime : 2019-12-31 00:00:00
             * lrcPath : http://open.migu.cn:8100/material/lyric/2016/06/23/1606231601302515.lrc?m
             * musicPath : http://tyst.migu.cn/public/ringmaker01/n14/music2/2016/06/2016%E5%B9%B406%E6%9C%8823%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E6%88%91%E5%A5%BD%E5%83%8F%E5%9C%A8%E5%93%AA%E8%A7%81%E8%BF%87%E4%BD%A0-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=9971ec99-3db5-41ce-abba-5b27c0c66307
             * musicPicPath : http://218.200.230.40:18089/files/album/2016-06-23/1606231610532476.jpg
             * musicSingerPicPath : http://218.200.230.40:18089/files/artist/2016-06-07/1606071102364397.jpg
             * name : 我好像在哪见过你
             * nameSpell : whxznjgn
             * sell : 2
             * setRingTone : [{"addTime":"2016-06-24 10:38:17","cooperation":"移动","id":"f39db5d355249e0201558045adbe00d1","initialid":"0fa13ce731261893a71ae5c3267cbab0","isTime":"2019-12-31 00:00:00","name":"我好像在哪见过你","nameSpell":"whxznjgn","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n14/music2/2016/06/2016%E5%B9%B406%E6%9C%8823%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E6%88%91%E5%A5%BD%E5%83%8F%E5%9C%A8%E5%93%AA%E8%A7%81%E8%BF%87%E4%BD%A0-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=73bcb92a-2b5c-45e3-b370-b74e5ca2eee9","ringToneSell":2,"singerName":"薛之谦","singerNameSpell":"xzq"}]
             * singerName : 薛之谦
             * singerNameSpell : xzq
             * valueFrom : 接口
             */

            private String addTime;
            private String cooperation;
            private String id;
            private String initialId;
            private String initialSingerId;
            private int isRecommend;
            private String isTime;
            private String lrcPath;
            private String musicPath;
            private String musicPicPath;
            private String musicSingerPicPath;
            private String name;
            private String nameSpell;
            private int sell;
            private String singerName;
            private String singerNameSpell;
            private String valueFrom;
            private List<SetRingToneEntity> setRingTone;

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public void setCooperation(String cooperation) {
                this.cooperation = cooperation;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setInitialId(String initialId) {
                this.initialId = initialId;
            }

            public void setInitialSingerId(String initialSingerId) {
                this.initialSingerId = initialSingerId;
            }

            public void setIsRecommend(int isRecommend) {
                this.isRecommend = isRecommend;
            }

            public void setIsTime(String isTime) {
                this.isTime = isTime;
            }

            public void setLrcPath(String lrcPath) {
                this.lrcPath = lrcPath;
            }

            public void setMusicPath(String musicPath) {
                this.musicPath = musicPath;
            }

            public void setMusicPicPath(String musicPicPath) {
                this.musicPicPath = musicPicPath;
            }

            public void setMusicSingerPicPath(String musicSingerPicPath) {
                this.musicSingerPicPath = musicSingerPicPath;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNameSpell(String nameSpell) {
                this.nameSpell = nameSpell;
            }

            public void setSell(int sell) {
                this.sell = sell;
            }

            public void setSingerName(String singerName) {
                this.singerName = singerName;
            }

            public void setSingerNameSpell(String singerNameSpell) {
                this.singerNameSpell = singerNameSpell;
            }

            public void setValueFrom(String valueFrom) {
                this.valueFrom = valueFrom;
            }

            public void setSetRingTone(List<SetRingToneEntity> setRingTone) {
                this.setRingTone = setRingTone;
            }

            public String getAddTime() {
                return addTime;
            }

            public String getCooperation() {
                return cooperation;
            }

            public String getId() {
                return id;
            }

            public String getInitialId() {
                return initialId;
            }

            public String getInitialSingerId() {
                return initialSingerId;
            }

            public int getIsRecommend() {
                return isRecommend;
            }

            public String getIsTime() {
                return isTime;
            }

            public String getLrcPath() {
                return lrcPath;
            }

            public String getMusicPath() {
                return musicPath;
            }

            public String getMusicPicPath() {
                return musicPicPath;
            }

            public String getMusicSingerPicPath() {
                return musicSingerPicPath;
            }

            public String getName() {
                return name;
            }

            public String getNameSpell() {
                return nameSpell;
            }

            public int getSell() {
                return sell;
            }

            public String getSingerName() {
                return singerName;
            }

            public String getSingerNameSpell() {
                return singerNameSpell;
            }

            public String getValueFrom() {
                return valueFrom;
            }

            public List<SetRingToneEntity> getSetRingTone() {
                return setRingTone;
            }

            public static class SetRingToneEntity implements Serializable{
                /**
                 * addTime : 2016-06-24 10:38:17
                 * cooperation : 移动
                 * id : f39db5d355249e0201558045adbe00d1
                 * initialid : 0fa13ce731261893a71ae5c3267cbab0
                 * isTime : 2019-12-31 00:00:00
                 * name : 我好像在哪见过你
                 * nameSpell : whxznjgn
                 * ringTonePath : http://tyst.migu.cn/public/ringmaker01/n14/music2/2016/06/2016%E5%B9%B406%E6%9C%8823%E6%97%A514%E7%82%B910%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E6%B5%B7%E8%9D%B6%E6%97%B6%E4%BB%A3%E6%95%B0%E7%A0%811%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E6%88%91%E5%A5%BD%E5%83%8F%E5%9C%A8%E5%93%AA%E8%A7%81%E8%BF%87%E4%BD%A0-%E8%96%9B%E4%B9%8B%E8%B0%A6.mp3?channelid=08&msisdn=73bcb92a-2b5c-45e3-b370-b74e5ca2eee9
                 * ringToneSell : 2
                 * singerName : 薛之谦
                 * singerNameSpell : xzq
                 */

                private String addTime;
                private String cooperation;
                private String id;
                private String initialid;
                private String isTime;
                private String name;
                private String nameSpell;
                private String ringTonePath;
                private int ringToneSell;
                private String singerName;
                private String singerNameSpell;

                public void setAddTime(String addTime) {
                    this.addTime = addTime;
                }

                public void setCooperation(String cooperation) {
                    this.cooperation = cooperation;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setInitialid(String initialid) {
                    this.initialid = initialid;
                }

                public void setIsTime(String isTime) {
                    this.isTime = isTime;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setNameSpell(String nameSpell) {
                    this.nameSpell = nameSpell;
                }

                public void setRingTonePath(String ringTonePath) {
                    this.ringTonePath = ringTonePath;
                }

                public void setRingToneSell(int ringToneSell) {
                    this.ringToneSell = ringToneSell;
                }

                public void setSingerName(String singerName) {
                    this.singerName = singerName;
                }

                public void setSingerNameSpell(String singerNameSpell) {
                    this.singerNameSpell = singerNameSpell;
                }

                public String getAddTime() {
                    return addTime;
                }

                public String getCooperation() {
                    return cooperation;
                }

                public String getId() {
                    return id;
                }

                public String getInitialid() {
                    return initialid;
                }

                public String getIsTime() {
                    return isTime;
                }

                public String getName() {
                    return name;
                }

                public String getNameSpell() {
                    return nameSpell;
                }

                public String getRingTonePath() {
                    return ringTonePath;
                }

                public int getRingToneSell() {
                    return ringToneSell;
                }

                public String getSingerName() {
                    return singerName;
                }

                public String getSingerNameSpell() {
                    return singerNameSpell;
                }
            }
        }
    }
}
