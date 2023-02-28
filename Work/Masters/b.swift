import UIKit

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
    }
    
    func setupUI() {
        // Create a background color for the view
        view.backgroundColor = .white
        
        // Add a label to the view
        let label = UILabel()
        label.text = "Welcome to My Homepage"
        label.font = UIFont.systemFont(ofSize: 24)
        label.textAlignment = .center
        view.addSubview(label)
        
        // Position the label in the center of the view
        label.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            label.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            label.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ])
        
        // Add a button to the view
        let button = UIButton(type: .system)
        button.setTitle("Learn More", for: .normal)
        button.addTarget(self, action: #selector(buttonTapped), for: .touchUpInside)
        view.addSubview(button)
        
        // Position the button below the label
        button.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            button.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            button.topAnchor.constraint(equalTo: label.bottomAnchor, constant: 20)
        ])
    }
    
    @objc func buttonTapped() {
        // Handle button tap here
    }
}
