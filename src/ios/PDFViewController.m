//
//  PDFViewController.m
//  环保“嘉”助手
//
//  Created by 熊竹 on 2018/6/20.
//

#import "PDFViewController.h"
#import <WebKit/WebKit.h>
#import "Masonry.h"

@interface PDFViewController ()
@property(nonatomic, strong) NSDictionary *options;
@end

@implementation PDFViewController
- (instancetype)initWithOptions:(NSDictionary *)options {
    if (self = [super init]) {
        _options = options;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    WKWebView *webView = [[WKWebView alloc] init];

    // 2.创建请求
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:_options[@"url"]]];

    // 3.加载网页
    [webView loadRequest:request];

    //最后将webView添加到界面
    [self.view addSubview:webView];

    [webView mas_makeConstraints:^(MASConstraintMaker *make) {
        if (@available(iOS 11.0, *)) {
            make.top.equalTo(self.view.mas_safeAreaLayoutGuideTop);
            make.left.equalTo(self.view.mas_safeAreaLayoutGuideLeft);
            make.right.equalTo(self.view.mas_safeAreaLayoutGuideRight);
            make.bottom.equalTo(self.view.mas_safeAreaLayoutGuideBottom);
        } else {
            make.edges.equalTo(self.view);
        }
    }];

    self.title = _options[@"title"];
    UIBarButtonItem *done = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemDone target:self action:@selector(done)];
    self.navigationItem.leftBarButtonItem = done;
}

- (void)done {
    [self.navigationController dismissViewControllerAnimated:YES completion:nil];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
