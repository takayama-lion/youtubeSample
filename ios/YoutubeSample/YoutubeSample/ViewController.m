//
//  ViewController.m
//  YoutubeSample
//
//  Created by 高山博行 on 2017/04/18.
//  Copyright © 2017年 高山博行. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    NSDictionary *playerVars = @{
                                 @"playsinline" : @1,
                                 };
    [self.playerView loadWithVideoId:@"gDgw9R6wZV4" playerVars:playerVars];
    [self.playerView2 loadWithVideoId:@"gDgw9R6wZV4"];
    self.playerView2.delegate = self;
    
    UITapGestureRecognizer *recognizer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onTapped:)];
    [self.ImageSample addGestureRecognizer:recognizer];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(IBAction)onButton:(id)sender
{
    [self.playerView2 playVideo];
}

- (void)playerView:(YTPlayerView *)playerView didChangeToState:(YTPlayerState)state {
    switch (state) {
        case kYTPlayerStateUnstarted:
            NSLog(@"kYTPlayerStateUnstarted");
            break;
        case kYTPlayerStateEnded:
            NSLog(@"kYTPlayerStateEnded");
            break;
        case kYTPlayerStatePlaying:
            NSLog(@"kYTPlayerStatePlaying");
            break;
        case kYTPlayerStatePaused:
            NSLog(@"kYTPlayerStatePaused");
            break;
        case kYTPlayerStateBuffering:
            NSLog(@"kYTPlayerStateBuffering");
            break;
        case kYTPlayerStateQueued:
            NSLog(@"kYTPlayerStateQueued");
            break;
        case kYTPlayerStateUnknown:
            NSLog(@"kYTPlayerStateUnknown");
            break;
        default:
            break;
    }
    NSLog(@"--%f", playerView.frame.size.height);
}
- (void)onTapped:(UITapGestureRecognizer *)recognizer {
    NSLog(@"------tap");
    [self.playerView2 playVideo];
}
@end
