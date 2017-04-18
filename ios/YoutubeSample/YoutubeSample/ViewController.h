//
//  ViewController.h
//  YoutubeSample
//
//  Created by 高山博行 on 2017/04/18.
//  Copyright © 2017年 高山博行. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "YTPlayerView.h"

@interface ViewController : UIViewController<YTPlayerViewDelegate>

@property(nonatomic, strong) IBOutlet YTPlayerView *playerView;
@property(nonatomic, strong) IBOutlet YTPlayerView *playerView2;
@property(nonatomic, strong) IBOutlet UIButton *button;
@property(nonatomic, strong) IBOutlet UIView *ImageSample; // 画像Viewの代わり

-(IBAction)onButton:(id)sender;
@end

