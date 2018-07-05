//
//  PDFController.m
//  环保“嘉”助手
//
//  Created by 熊竹 on 2018/6/20.
//

#import "PDFNavigationController.h"
#import <WebKit/WebKit.h>

@interface PDFNavigationController ()

@end

@implementation PDFNavigationController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(BOOL)isNavigationBarHidden{
    return NO;
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
