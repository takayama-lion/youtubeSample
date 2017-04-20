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
//                                 @"playsinline" : @1,
                                 @"hl": @"ja"
                                 };
    [self.playerView loadWithVideoId:@"gDgw9R6wZV4" playerVars:playerVars];
    [self.playerView2 loadWithVideoId:@"gDgw9R6wZV4" ];
    self.playerView2.delegate = self;
    
    
    UITapGestureRecognizer *recognizer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onTapped:)];
    [self.ImageSample addGestureRecognizer:recognizer];
    self.Operation = NO;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(IBAction)onButton:(id)sender
{
    [self.playerView2 playVideo];
    self.Operation = YES;
}

- (void)playerView:(YTPlayerView *)playerView didChangeToState:(YTPlayerState)state {
    switch (state) {
        case kYTPlayerStateUnstarted:
            NSLog(@"---kYTPlayerStateUnstarted");
            break;
        case kYTPlayerStateEnded:
            NSLog(@"---kYTPlayerStateEnded");
            break;
        case kYTPlayerStatePlaying:
            NSLog(@"---kYTPlayerStatePlaying");
            break;
        case kYTPlayerStatePaused:
            NSLog(@"---kYTPlayerStatePaused");
            break;
        case kYTPlayerStateBuffering:
            NSLog(@"---kYTPlayerStateBuffering");
            break;
        case kYTPlayerStateQueued:
            NSLog(@"---kYTPlayerStateQueued");
            break;
        case kYTPlayerStateUnknown:
            NSLog(@"---kYTPlayerStateUnknown");
            break;
        default:
            NSLog(@"---default");
            break;
    }
    //NSLog(@"--%f", playerView.frame.size.height);
    //NSLog(@"--class=[%@]",playerView.subviews[0].class);
    UIWebView *webView = playerView.subviews[0];
//    NSString* str = [webView stringByEvaluatingJavaScriptFromString:@"document.body.innerHTML"];
  //  NSLog(@"--url=[%@]", str);
    
   // NSString *result = [webView stringByEvaluatingJavaScriptFromString:@"document.body.offsetHeight;"];
    
   // int height = [result integerValue];
   //   NSLog(@"--height=[%d]", height);
    //NSLog(@"--webView height: %@", [webView stringByEvaluatingJavaScriptFromString: @"document.body.offsetHeight"]);
    //NSLog(@"--webView height: %@", [webView stringByEvaluatingJavaScriptFromString: @"document.height"]);
    NSLog(@"----query=[%d]", [playerView.subviews count]);
}
- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    NSLog(@"=-----!!---");
}
- (void)onTapped:(UITapGestureRecognizer *)recognizer {
    NSLog(@"------tap");
    [self.playerView2 playVideo];
}
-(void)didRotateFromInterfaceOrientation:(UIInterfaceOrientation)FromInterfaceOrientation {
    if(FromInterfaceOrientation == UIInterfaceOrientationPortrait){
        // 横向き
        //NSLog(@"--横");
    } else {
        // 縦向き
        //NSLog(@"--横");
    }
}
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    //NSLog(@"--回転開始");
    return YES;
}
- (BOOL)shouldAutorotate
{
    NSLog(@"-------shou");
//    return YES;
    return self.Operation;
}

/**
 * Invoked when the player view is ready to receive API calls.
 *
 * @param playerView The YTPlayerView instance that has become ready.
 */
- (void)playerViewDidBecomeReady:(nonnull YTPlayerView *)playerView
{
    NSLog(@"---Invoked when the player view is ready to receive API calls.");
}

/**
 * Callback invoked when playback quality has changed.
 *
 * @param playerView The YTPlayerView instance where playback quality has changed.
 * @param quality YTPlaybackQuality designating the new playback quality.
 */
- (void)playerView:(nonnull YTPlayerView *)playerView didChangeToQuality:(YTPlaybackQuality)quality
{
    NSLog(@"---Callback invoked when playback quality has changed.");
    
}

/**
 * Callback invoked when an error has occured.
 *
 * @param playerView The YTPlayerView instance where the error has occurred.
 * @param error YTPlayerError containing the error state.
 */
- (void)playerView:(nonnull YTPlayerView *)playerView receivedError:(YTPlayerError)error
{
    NSLog(@"---playerView receivedError");
    
}

/**
 * Callback invoked frequently when playBack is plaing.
 *
 * @param playerView The YTPlayerView instance where the error has occurred.
 * @param playTime float containing curretn playback time.
 */
- (void)playerView:(nonnull YTPlayerView *)playerView didPlayTime:(float)playTime
{
    //NSLog(@"---Callback invoked frequently when playBack is plaing ");
    
}

@end
